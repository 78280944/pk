package com.lottery.api.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

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
		StringBuffer PostKey = new StringBuffer();
		//获取支付银行（微信支付暂时加在此处）
		//String P_Description =(String) request.getParameter("pd_FrpId");
		String P_Description ="";
		//获取支付金额
		//Float P_FaceValue = Float.parseFloat(request.getParameter("faceValue"));
		Float P_FaceValue = accountRecharge.getTransamt().floatValue();
		//String ChannelId = request.getParameter("P_ChannelId");
		int P_ChannelId = Integer.valueOf(accountRecharge.getProductid());
		if (P_ChannelId == 92)
			P_Description = "0";
		else 
			P_Description = "1";
		/*
		//判断是否是微信支付
		if(21==Integer.parseInt(P_Description)){//21是微信支付，否则是网银支付
			P_ChannelId = 21;
		}else{
			P_ChannelId = 1;
		}*/
		//获取商户ID
		int P_UserId = Integer.parseInt(Utils.readProp("P_UserId"));
		accountRecharge.setMerno(String.valueOf(P_UserId));
		//订单号
		String P_OrderId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		accountRecharge.setOrderno(P_OrderId);
		accountRecharge.setOrderdate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		accountRecharge.setRequestno(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		accountRecharge.setReturnurl(Utils.readProp("P_Result_URL"));
		accountRecharge.setNotifyurl(Utils.readProp("P_Notify_URL"));
		accountRecharge.setCommodityname(accountRecharge.getAccountid()+",充值金额："+ (accountRecharge.getTransamt())+",IP:"+accountRecharge.getOrderip());
		accountRecharge.setRemark("充值金额:"+(accountRecharge.getTransamt())+",充值时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		//卡类充值时的卡号    （卡类充值时必须，否则可为空）
		String P_CardId = "0";
		//卡类充值时的卡密    （卡类充值时必须，否则可为空）
		String P_CardPass = "0";
		//alfStr是您的安全码，如安全码被泄漏可通过平台重置
		String SalfStr = Utils.readProp("SalfStr");
		//拼装参数   （必须按照“参与签名”状态为“是”的参数按其顺序用“|”组合，最后加上用户密钥，然后进行32位的md5编码）
		PostKey.append(P_UserId).append("|").append(P_OrderId).append("|").append(P_CardId).append("|").append(P_CardPass).append("|").append(P_FaceValue).append("|").append(P_ChannelId).append("|").append(SalfStr);
		//进行32位的md5编码
		System.out.println("12---"+PostKey);
		String P_PostKey = MD5.encryption(PostKey.toString());
		System.out.println("1234---"+P_PostKey);
		String target = Utils.readProp("target");
		Float P_Price = (float)1.00;
		String P_Subject = "0";
		String param = "P_UserId="+P_UserId+"&P_OrderId="+P_OrderId+"&P_CardId="+P_CardId+"&P_CardPass="+P_CardPass+"&P_FaceValue="+P_FaceValue+"&P_ChannelId="+P_ChannelId+"&SalfStr="+SalfStr;
		String otherparam = "&P_Description="+P_Description
				+"&P_Price="+P_Price+"&P_Subject="+P_Subject
				+"&P_Result_URL="+accountRecharge.getReturnurl()+"&P_Notify_URL="+accountRecharge.getNotifyurl()+"&P_PostKey="+P_PostKey;
		target = target+"?"+param+otherparam;
		/*
				"?P_UserId="+P_UserId+"&P_CardId="+P_CardId+"&P_CardPass="+P_CardPass+"&P_FaceValue="+P_FaceValue
				+"&P_ChannelId="+P_ChannelId+"&SalfStr="+SalfStr+"&P_OrderId="+P_OrderId+"&P_Description="+P_Description
				+"&P_Price="+P_Price+"&P_Subject="+P_Subject
				+"&P_Result_URL="+P_Result_URL+"&P_Notify_URL="+P_Result_URL+"&P_PostKey="+P_PostKey;*/
		accountRecharge.setMweburl(target);
		accountRecharge.setSignature(P_PostKey);
		System.out.println("1."+accountRecharge.getMweburl());
		return accountRecharge;
		
	}
	
	@SuppressWarnings("deprecation")
	public synchronized  String getPayResults(AccountRecharge aRecharge) throws Exception{
		try{
			String  userHttpUrl  = Utils.readProp("QueryStr");
				
			HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

			 DefaultHttpClient client = new DefaultHttpClient();
			 SchemeRegistry registry = new SchemeRegistry();
			 SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
			 socketFactory.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
			 registry.register(new Scheme("https", socketFactory, 443));
			 SingleClientConnManager mgr = new SingleClientConnManager(client.getParams(), registry);
			 DefaultHttpClient httpClient = new DefaultHttpClient(mgr, client.getParams());

			 // Set verifier     
			 HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
			 HttpPost post = new HttpPost(userHttpUrl);
			 StringBuffer PostKey = new StringBuffer();
			 String P_UserId = aRecharge.getMerno();
			 String P_OrderId = aRecharge.getOrderno();
			 String P_ChannelId = aRecharge.getProductid();
			 String P_CardId = "0";
			 String P_FaceValue = String.valueOf(aRecharge.getTransamt());
			 String SalfStr = Utils.readProp("SalfStr");
			 String P_PostKey="P_UserId="+P_UserId+"&P_OrderId="+P_OrderId+"&P_ChannelId="+P_ChannelId+"&P_CardId="+P_CardId+"&P_FaceValue="+P_FaceValue
						+"&P_PostKey="+SalfStr;
			 P_PostKey = MD5.encryption(P_PostKey.toString());
			 PostKey.append(P_UserId).append("|").append(P_OrderId).append("|").append(P_ChannelId).append("|").append(P_CardId).append("|").append(P_FaceValue).append("|").append(SalfStr);
				//进行32位的md5编码
			 System.out.println(PostKey);
			 
			 List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		        nvps.add(new BasicNameValuePair("P_UserId", aRecharge.getMerno()));
		        nvps.add(new BasicNameValuePair("P_OrderId", aRecharge.getOrderno()));
		        nvps.add(new BasicNameValuePair("P_ChannelId", aRecharge.getProductid()));
		        nvps.add(new BasicNameValuePair("P_CardId", "0"));
		        nvps.add(new BasicNameValuePair("P_FaceValue", String.valueOf(aRecharge.getTransamt())));
		        nvps.add(new BasicNameValuePair("SalfStr", SalfStr));
		        nvps.add(new BasicNameValuePair("P_PostKey", P_PostKey));
		        
		        /*
	        // 接收参数json列表
	        JSONObject jsonParam = new JSONObject();
	        System.out.println("merno="+aRecharge.getMerno());
	        jsonParam.put("P_UserId", "1004044");
	        jsonParam.put("P_OrderId", aRecharge.getOrderno());
	        jsonParam.put("P_CardId", "0");
	        jsonParam.put("P_FaceValue", aRecharge.getTransamt());
	        jsonParam.put("P_ChannelId", aRecharge.getProductid());
	        jsonParam.put("P_PostKey", aRecharge.getSignature());
	        
	        
	        StringEntity entity = new StringEntity(jsonParam.toString(), "UTF-8");// 解决中文乱码问题
	        entity.setContentEncoding("UTF-8");
	        entity.setContentType("application/json");
	        post.setEntity(entity);
	  */
		    post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            
	        HttpResponse ret = httpClient.execute(post);
	        String tokenRet = EntityUtils.toString(ret.getEntity(), "UTF-8");
	        System.out.println("124-"+tokenRet);
	       // Map mapTypes = JSON.parseObject(tokenRet);
	        String[] mapTypes = tokenRet.split("&");
	        String P_flag = "";
	        String P_status = "";
	        for (int i=0;i<mapTypes.length;i++){
	        	if (mapTypes[i].contains("P_flag")){
	        		String[] ms = mapTypes[i].split("=");
	        		if (null==ms[1])
	        			P_flag = "0";
	        		else
	        			P_flag = ms[1];
	        		P_flag = ms[1];
	        	}else if (mapTypes[i].contains("P_status")){
	        		String[] ms = mapTypes[i].split("=");
	        		if (null==ms[1])
	        			P_status = "0";
	        		else
	        			P_status = ms[1];
	        	}        		
	        }
	        /*
	            for (Object obj : mapTypes.keySet()){  
	                if((obj.toString().equals("P_flag"))){
	                	P_flag = mapTypes.get(obj).toString();
	                }else if ((obj.toString().equals("P_status"))){
	                	P_status = mapTypes.get(obj).toString();      	  
	                }    
	         	}
	         	*/
	        System.out.println("A="+P_flag+",B="+P_status);
	            if (P_flag.equals("0")&&P_status.equals("0")){
	            	aRecharge.setRespcode("P000");
	            	aRecharge.setRespdesc("处理中");
	            }else if (P_flag.equals("1")&&P_status.equals("0")){
	            	aRecharge.setRespcode("9998");
	            	aRecharge.setRespcode("支付失败");
	            }
	            else if (P_flag.equals("1")&&P_status.equals("1")){
	            	aRecharge.setRespcode("0000");
	            	aRecharge.setRespdesc("支付成功");
	            }
	            aRecharge.setPayno(aRecharge.getOrderno());
	            if (aRecharge.getRespcode().equals("0000")){
	            	
	                if (aRecharge.getRelativetype().equals("In")){
	                	String messages = accountInfoService.checkResult(aRecharge.getOrderno(), aRecharge.getPayno(), String.valueOf(aRecharge.getTransamt()), aRecharge.getOrderdate(), aRecharge.getRespcode(), aRecharge.getRespdesc());
	                	System.out.println(messages+",充值,arid="+aRecharge.getArid());
	                	LOG.info(messages+",充值,arid="+aRecharge.getArid());
	                }else if (aRecharge.getRelativetype().equals("Out")){
	                	System.out.println("取现,arid="+aRecharge.getArid());
	                	LOG.info("取现,arid="+aRecharge.getArid());
	                }
	                aRecharge.setOrderstate("01");
	            }else if (aRecharge.getRespcode().equals("P000")){
	                
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
		   
	
	@SuppressWarnings("deprecation")
	public synchronized  String getCashResults(AccountRecharge aRecharge) throws Exception{
		try{
			String  userHttpUrl = Utils.readProp("CashQueryStr");
			
			SubmitReturn subReturn = new SubmitReturn();
			String returnMsg = "";
			String version = "2";	    //必填	当前接口版本号 2
			String agent_id = Utils.readProp("P_UserId");	//必填	商户编号如1001
			String key = Utils.readProp("key");//密钥需要商户替换为自己的密钥
			String batch_no = aRecharge.getOrderno();	//必填	批量付款定单号（要保证唯一）。长度最长50字符	11<批量付款定单号<50
			
			
			QueryModel model = new QueryModel();
			model.setVersion(version);
			model.setAgent_id(agent_id);
			model.setBatch_no(batch_no);
			
			
			subReturn = QuerysitSubmit.SubmitUrl(model,key);//处理请求
			
			if(subReturn.is_success())
		    {
		    	System.out.println(subReturn.get_error_message());//出现错误打印出错误信息
		    }
		    else
		    {
		    	//提交成功后，返回的支付信息，商户需要验证签名和金额，来处理自己的业务逻辑
		    	//此处返回值为xml 此处未完善。需要商户自己处理解析xml
		    	System.out.println(subReturn.get_error_message());
		    	LOG.info(new Date()+","+subReturn.get_error_message()+",订单号："+aRecharge.getOrderno());
		    	returnMsg = subReturn.get_error_message();
		    	Map map = Md5Tools.checkPostKey(returnMsg,key);
			        if (map.get("signFlag").toString().equals("true")) {
			   		     System.out.println(new Date()+",验签成功,"+aRecharge.getOrderno());
			            //验签成功后解析返回的字段 
			      	}else{
			      		System.out.println(new Date()+",验签失败,"+aRecharge.getOrderno());
			      		return "false";
			      	}
			        // Map mapTypes = JSON.parseObject(tokenRet);
			        aRecharge.setPayno(map.get("hy_bill_no").toString());
			        String detail_data = map.get("detail_data").toString();
			        System.out.println("detail_data="+detail_data);
			        String P_status = detail_data.substring(detail_data.length()-1, detail_data.length());
			        System.out.println("P_status="+P_status);
			            if (P_status.toUpperCase().equals("S")){
			            	aRecharge.setRespcode("0000");
			            	aRecharge.setRespdesc("交易成功");
			            }else if (P_status.toUpperCase().equals("F")){
			            	aRecharge.setRespcode("9998");
			            	aRecharge.setRespcode("交易失败");
			            }else if (P_status.toUpperCase().equals("P")){
			            	aRecharge.setRespcode("P000");
			            	aRecharge.setRespdesc("处理中");
			            }

			            if (aRecharge.getRespcode().equals("0000")){
			                 if (aRecharge.getRelativetype().equals("Out")){
			                	System.out.println("取现,arid="+aRecharge.getArid());
			                	LOG.info("取现,arid="+aRecharge.getArid());
			                }
			                aRecharge.setOrderstate("01");
			            }else if (aRecharge.getRespcode().equals("P000")){
			                
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
		    }
			

	       
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
