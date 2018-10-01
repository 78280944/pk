package com.lottery.api.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.JAXBException;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jetpay.model.netpayment.RequestNetPayMent;
import com.jetpay.model.queryorder.RequestQueryOrder;
import com.jetpay.model.queryorder.ResponseQueryOrder;
import com.jetpay.service.netpayment.NetPayMentService;
import com.jetpay.service.queryorder.QueryOrderService;
import com.jetpay.utils.DateUtil;
import com.jetpay.utils.MapJavaObjectConverrter;
import com.jetpay.utils.RandomUtil;
import com.jetpay.utils.SecurityUtil;
import com.jetpay.utils.XmlObjMcUtil;
import com.kdpay.util.MD5;
import com.kdpay.util.Utils;
import com.lottery.api.dto.QueryModel;
import com.lottery.api.dto.SubmitReturn;
import com.lottery.api.util.Md5Tools;
import com.lottery.orm.bo.AccountInfo;
import com.lottery.orm.bo.AccountRecharge;
import com.lottery.orm.dao.AccountInfoMapper;
import com.lottery.orm.dao.AccountRechargeMapper;
import com.lottery.orm.service.AccountInfoService;
import com.lottery.orm.util.EnumType;


@Service
@Transactional
public class TransPayTest {
	public static final Logger LOG = Logger.getLogger(TransPayTest.class);
	
	@Autowired
    private AccountRechargeMapper accountRechargeMapper;
	
	@Autowired
    private AccountInfoMapper accountInfoMapper;
	
	@Autowired
    private AccountInfoService accountInfoService;
	
	public synchronized AccountRecharge getPayUrl(AccountRecharge accountRecharge){
		RequestNetPayMent pay=new RequestNetPayMent();
		//交易名称，固定填netpayment
		//int amtl=accountRecharge.getTransamt();
		pay.setAmount(accountRecharge.getTransamt().toString());
		pay.setCurrency(Utils.readProp("currency"));
		pay.setMerCode(Utils.readProp("merCode"));
		pay.setNotifyURL(Utils.readProp("notifyURL"));
		//产品描述
		pay.setOrderDesc("充值"+accountRecharge.getAccountid());
		//订单号，本Demo随机生成字符串来填充,本demo 的orderno只做参考
		String orderno=DateUtil.format(DateUtil.getCurrDate(), DateUtil.formatStr_yyyyMMddHHmmss1)
		+RandomUtil.nextChar()+RandomUtil.nextChar()+RandomUtil.nextIntAsStringByLength(5);
		pay.setOrderNo(orderno);
		//本Demo这里获取系统的时间,进行格式化转换
		pay.setOrderTime(DateUtil.format(DateUtil.getCurrDate(), DateUtil.formatStr_yyyyMMddHHmmss1));
		//交易类型
		pay.setPayType(accountRecharge.getProductid());
		
		//商品名称自定
		pay.setProductName("网银支付"+accountRecharge.getAccountid());
		pay.setReturnURL(Utils.readProp("returnURL"));
		pay.setTranName(Utils.readProp("tranName"));
		//版本号固定，最低版本为1.00
		//pay.setVersion(JetPayConfig.props.get("version").toString().trim());
		pay.setVersion(Utils.readProp("version"));
		String res=NetPayMentService.netpayservice.getRequestParams(pay);
		String payurl = Utils.readProp("payurl");
		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append("<html><head><title>pay</title></head><body>");
		sbHtml.append("<form id=\"form\" target=\"_blank\" name=\"form1\" action=\"" +payurl + "\" method=\"post\">");
		sbHtml.append("<input type=\"hidden\" name=\"tranType\" value=\"payment\"/>");
		sbHtml.append("<input type=\"hidden\" name=\"param\" value='" +res + "'/>");
		
		sbHtml.append("<input type=\"submit\" value=\"submit\" style=\"display:none;\"></form>");
		sbHtml.append("<script>document.forms['form'].submit();</script>");
		sbHtml.append("</body></html>");
		
		LOG.info("请求表单已生成："+sbHtml.toString());
		
		accountRecharge.setRequestno(pay.getOrderNo());
		accountRecharge.setVersion(pay.getVersion());
		accountRecharge.setMerno(pay.getMerCode());
		accountRecharge.setOrderdate(pay.getOrderTime());
		accountRecharge.setOrderno(pay.getOrderNo());
		accountRecharge.setReturnurl(pay.getReturnURL());
		accountRecharge.setNotifyurl(pay.getNotifyURL());
		accountRecharge.setCommodityname(accountRecharge.getAccountid()+",充值金额："+accountRecharge.getTransamt()/100);
		accountRecharge.setMweburl(sbHtml.toString());
		accountRecharge.setRespcode("P000");
		accountRecharge.setRespdesc("交易处理中");
		accountRecharge.setRemark("充值金额："+accountRecharge.getTransamt()/100+",充值时间："+pay.getOrderTime());
		accountRecharge.setSignature(res);
		//renderHtml(sbHtml.toString());
		return accountRecharge;
	}
	
	@SuppressWarnings("deprecation")
	public synchronized  String getPayResults(AccountRecharge aRecharge) throws Exception{
		
		try{	        
	        RequestQueryOrder reqqueryorder=new RequestQueryOrder();
			reqqueryorder.setTranName("queryOrder");
			reqqueryorder.setVersion(aRecharge.getVersion());
			reqqueryorder.setMerCode(aRecharge.getMerno());
			reqqueryorder.setOrderNo(aRecharge.getOrderno());
			reqqueryorder.setOrderType("payment");
			
			//自然排序
			//把pay装换成Map<String,String>,用于下面首字母排序
			Map<String,String> params=MapJavaObjectConverrter.objectToMapString(reqqueryorder, true);
			//转换成请求的签名原串
			String paramsStr=MapJavaObjectConverrter.mapStringKeySortToLinkString(params, true);
			String merKey = Utils.readProp("merKey");
			LOG.info(new String("签名原串+key："+paramsStr+"&key="+merKey));
			
			//生成签名
			reqqueryorder.setSign(SecurityUtil.MD5Hex(paramsStr+ "&key="+merKey));
			LOG.info("SginMsg："+reqqueryorder.getSign());
			
			//xml请求数据
			String data;
				
			data = XmlObjMcUtil.ObjectToXml(reqqueryorder, "UTF-8");
			LOG.info("RequertData:Data["+data+"]");
			
			Map rquestparams=new HashMap<String, String>();
			rquestparams.put("tranType", reqqueryorder.getTranName());
			rquestparams.put("param", data);
			
			//启动server 进行Post请求
			String returnres=QueryOrderService.queryorderservice.queryOrder(Utils.readProp("merKey"), rquestparams);
			LOG.info("订单查询的返回数据为,returnres["+returnres+"]");
			
			ResponseQueryOrder respqueryorder=XmlObjMcUtil.converyToJavaBean(returnres, ResponseQueryOrder.class);
			boolean checksgin=QueryOrderService.queryorderservice.checkCallBackSgin(respqueryorder);
			if(checksgin){
				LOG.info("验签成功");
				if (respqueryorder.getTranStateCode().equals("00")){
	            	aRecharge.setRespcode("00");
	            	aRecharge.setRespdesc("支付成功");
				}else if (respqueryorder.getTranStateCode().equals("01")){
	            	aRecharge.setRespcode("01");
	            	aRecharge.setRespdesc("支付失败");
				}else if (respqueryorder.getTranStateCode().equals("02")){
	            	aRecharge.setRespcode("02");
	            	aRecharge.setRespdesc("不确定");
				}
			}else{
				LOG.info("验签失败");
				return "false";
			}
			
            aRecharge.setPayno(respqueryorder.getFlowNo());
            if (aRecharge.getRespcode().equals("00")){
            	
                if (aRecharge.getRelativetype().equals("In")){
                	String messages = accountInfoService.checkResult(aRecharge.getOrderno(), aRecharge.getPayno(), String.valueOf(aRecharge.getTransamt()), aRecharge.getOrderdate(), aRecharge.getRespcode(), aRecharge.getRespdesc());
                	System.out.println(messages+",充值,arid="+aRecharge.getArid());
                	LOG.info(messages+",充值,arid="+aRecharge.getArid());
                }
                aRecharge.setOrderstate("01");
            }else if (aRecharge.getRespcode().equals("P000")||aRecharge.getRespcode().equals("02")){
                
            }else{
            	AccountRecharge ar = accountRechargeMapper.selectByOrderNoReturn(aRecharge.getOrderno(), "In");
            	if (null == ar){
      		      LOG.info("该订单信息有误！");
      		      return "false";
          	     }
            	if (ar.getOrderstate().equals("01")||ar.getOrderstate().equals("02"))
            		return "true";
                if (aRecharge.getRelativetype().equals("In")){
                	AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(aRecharge.getAccountid());
                	aInfo.setUsermoney(aInfo.getUsermoney().add(BigDecimal.valueOf((double)(aRecharge.getTransamt()))));
            		aRecharge.setAccountamount(aInfo.getUsermoney());
            		accountInfoMapper.updateByPrimaryKey(aInfo);
        	    	if (aRecharge.getFee()>0){
        	    		aInfo = accountInfoMapper.selectByPrimaryKey(1000);
        	    		aInfo.setUsermoney(aInfo.getUsermoney().subtract(BigDecimal.valueOf(aRecharge.getFee())));
        		    	accountInfoMapper.updateByPrimaryKey(aInfo);
        	    	}
            	}
            	aRecharge.setOrderstate("02");
            }
            
            accountRechargeMapper.updateByPrimaryKey(aRecharge);
            System.out.println("验签成功,arid = "+aRecharge.getArid());
            LOG.info("验签成功,arid="+aRecharge.getArid());
            return "true";
    } catch (Exception e) {
        e.printStackTrace();
    }
	return "fasle";
	}
		   
	
	@SuppressWarnings("deprecation")
	public synchronized  String getCashResults(AccountRecharge aRecharge) throws Exception{
		
		try{	        
	        RequestQueryOrder reqqueryorder=new RequestQueryOrder();
			reqqueryorder.setTranName("queryOrder");
			reqqueryorder.setVersion(aRecharge.getVersion());
			reqqueryorder.setMerCode(aRecharge.getMerno());
			reqqueryorder.setOrderNo(aRecharge.getOrderno());
			reqqueryorder.setOrderType("agentpay");
			
			//自然排序
			//把pay装换成Map<String,String>,用于下面首字母排序
			Map<String,String> params=MapJavaObjectConverrter.objectToMapString(reqqueryorder, true);
			//转换成请求的签名原串
			String paramsStr=MapJavaObjectConverrter.mapStringKeySortToLinkString(params, true);
			String merKey = Utils.readProp("merKey");
			LOG.info(new String("签名原串+key："+paramsStr+"&key="+merKey));
			
			//生成签名
			reqqueryorder.setSign(SecurityUtil.MD5Hex(paramsStr+ "&key="+merKey));
			LOG.info("SginMsg："+reqqueryorder.getSign());
			
			//xml请求数据
			String data;
				
			data = XmlObjMcUtil.ObjectToXml(reqqueryorder, "UTF-8");
			LOG.info("RequertData:Data["+data+"]");
			
			Map rquestparams=new HashMap<String, String>();
			rquestparams.put("tranType", reqqueryorder.getTranName());
			rquestparams.put("param", data);
			
			//启动server 进行Post请求
			String returnres=QueryOrderService.queryorderservice.queryOrder(Utils.readProp("merKey"), rquestparams);
			LOG.info("订单查询的返回数据为,returnres["+returnres+"]");
			
			ResponseQueryOrder respqueryorder=XmlObjMcUtil.converyToJavaBean(returnres, ResponseQueryOrder.class);
			boolean checksgin=QueryOrderService.queryorderservice.checkCallBackSgin(respqueryorder);
			if(checksgin){
				LOG.info("验签成功");
				if (respqueryorder.getTranStateCode().equals("00")){
	            	aRecharge.setRespcode("00");
	            	aRecharge.setRespdesc("支付成功");
				}else if (respqueryorder.getTranStateCode().equals("01")){
	            	aRecharge.setRespcode("01");
	            	aRecharge.setRespdesc("支付失败");
				}else if (respqueryorder.getTranStateCode().equals("02")){
	            	aRecharge.setRespcode("02");
	            	aRecharge.setRespdesc("不确定");
				}
			}else{
				LOG.info("验签失败");
				return "false";
			}
			
            aRecharge.setPayno(respqueryorder.getFlowNo());
            if (aRecharge.getRespcode().equals("00")){
                 if (aRecharge.getRelativetype().equals("Out")){
                	System.out.println("取现,arid="+aRecharge.getArid());
                	LOG.info("取现,arid="+aRecharge.getArid());
                }
                aRecharge.setOrderstate("01");
            }else if (aRecharge.getRespcode().equals("P000")||aRecharge.getRespcode().equals("02")){
                
            }else{
            	AccountRecharge ar = accountRechargeMapper.selectByOrderNoReturn(aRecharge.getOrderno(), "Out");
            	if (null == ar){
      		      LOG.info("该订单信息有误！");
      		      return "false";
          	     }
            	if (ar.getOrderstate().equals("01")||ar.getOrderstate().equals("02"))
            		return "true";
                if (aRecharge.getRelativetype().equals("Out")){
                	AccountInfo aInfo = accountInfoMapper.selectByPrimaryKey(aRecharge.getAccountid());
                	aInfo.setUsermoney(aInfo.getUsermoney().add(BigDecimal.valueOf((double)(aRecharge.getTransamt()))));
            		aRecharge.setAccountamount(aInfo.getUsermoney());
            		accountInfoMapper.updateByPrimaryKey(aInfo);
        	    	if (aRecharge.getFee()>0){
        	    		aInfo = accountInfoMapper.selectByPrimaryKey(1000);
        	    		aInfo.setUsermoney(aInfo.getUsermoney().subtract(BigDecimal.valueOf(aRecharge.getFee())));
        		    	accountInfoMapper.updateByPrimaryKey(aInfo);
        	    	}
            	}
            	aRecharge.setOrderstate("02");
            }
            
            accountRechargeMapper.updateByPrimaryKey(aRecharge);
            System.out.println("验签成功,arid = "+aRecharge.getArid());
            LOG.info("验签成功,arid="+aRecharge.getArid());
            return "true";
	       
    } catch (Exception e) {
        e.printStackTrace();
    }
	return "fasle";
	}
	
	public static void main(String args[]) throws Exception{
		//String messages = accountInfoService.checkResult
		//(aRecharge.getOrderno(), aRecharge.getPayno(), String.valueOf(aRecharge.getTransamt()),
		//aRecharge.getOrderdate(), aRecharge.getRespcode(), aRecharge.getRespdesc());
		//20180313143821
		AccountRecharge accountRecharge = new AccountRecharge();
		accountRecharge.setAccountid(1835);
		accountRecharge.setTransamt(50);
		accountRecharge.setProductid("32");
		accountRecharge.setOrderno("20180314235103");
		accountRecharge.setMerno("1004044");
		accountRecharge.setRelativetype("Out");
		//accountRecharge.setPayno("20180311091910");
		accountRecharge.setOrderdate("20180314");
		TransPayTest a = new TransPayTest();
		String result = a.getCashResults(accountRecharge);
		System.out.println("12---"+result);
		
	}
}
