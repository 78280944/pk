package com.jetpay.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.jetpay.config.JetPayConfig;
import com.jetpay.model.netpayment.ResponseNetPayMent;
import com.jetpay.model.queryorder.RequestQueryOrder;
import com.jetpay.model.queryorder.ResponseQueryOrder;
import com.jetpay.service.queryorder.QueryOrderService;
import com.jetpay.utils.MapJavaObjectConverrter;
import com.jetpay.utils.SecurityUtil;
import com.jetpay.utils.XmlObjMcUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.kdpay.util.Utils;
import com.lottery.orm.bo.AccountRecharge;

public class QueryOrderServlet extends Controller{

	private static Logger logger=Logger.getLogger(QueryOrderServlet.class);
	public void index(){
		render("/main/queryorder.html");
	}
	
	//订单查询请求
	@Clear
	public void doRqqueryOrder(){
	
		//请求参数，获取所有的表单提交的参数
		RequestQueryOrder reqqueryorder=new RequestQueryOrder();
		reqqueryorder.setTranName(getPara("tranname").trim());
		reqqueryorder.setVersion(getPara("version").trim());
		reqqueryorder.setMerCode(getPara("merCode").trim());
		reqqueryorder.setOrderNo(getPara("orderNo").trim());
		reqqueryorder.setOrderType(getPara("orderType").trim());
		
		//自然排序
		//把pay装换成Map<String,String>,用于下面首字母排序
		Map<String,String> params=MapJavaObjectConverrter.objectToMapString(reqqueryorder, true);
		//转换成请求的签名原串
		String paramsStr=MapJavaObjectConverrter.mapStringKeySortToLinkString(params, true);
		
		logger.info(new String("签名原串+key："+paramsStr+"&key="+JetPayConfig.props.get("merKey").toString()));
		
		//生成签名
		reqqueryorder.setSign(SecurityUtil.MD5Hex(paramsStr+ "&key="+JetPayConfig.props.get("merKey").toString()));
		logger.info("SginMsg："+reqqueryorder.getSign());
		
		//xml请求数据
		String data;
		try {
			
			data = XmlObjMcUtil.ObjectToXml(reqqueryorder, "UTF-8");
			logger.info("RequertData:Data["+data+"]");
			
			Map rquestparams=new HashMap<String, String>();
			rquestparams.put("tranType", reqqueryorder.getTranName());
			rquestparams.put("param", data);
			
			//启动server 进行Post请求
			String returnres=QueryOrderService.queryorderservice.queryOrder(JetPayConfig.props.get("payurl").toString(), rquestparams);
			logger.info("订单查询的返回数据为,returnres["+returnres+"]");
			
			ResponseQueryOrder respqueryorder=XmlObjMcUtil.converyToJavaBean(returnres, ResponseQueryOrder.class);
			boolean checksgin=QueryOrderService.queryorderservice.checkCallBackSgin(respqueryorder);
			if(checksgin){
				logger.info("验签成功");
				setAttr("RespQueryOrder", respqueryorder);
				render("/main/queryorder_success.html");
			}else{
				logger.info("验签失败");
			}
			
		} catch (JAXBException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.error("请求数据data转换失败:"+e);
		}	
	}

	//订单查询请求
	@Clear
	public void doPayqueryOrder(AccountRecharge aRecharge){
		//请求参数，获取所有的表单提交的参数
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
		logger.info(new String("签名原串+key："+paramsStr+"&key="+merKey));
		
		//生成签名
		reqqueryorder.setSign(SecurityUtil.MD5Hex(paramsStr+ "&key="+merKey));
		logger.info("SginMsg："+reqqueryorder.getSign());
		
		//xml请求数据
		String data;
		try {
			
			data = XmlObjMcUtil.ObjectToXml(reqqueryorder, "UTF-8");
			logger.info("RequertData:Data["+data+"]");
			
			Map rquestparams=new HashMap<String, String>();
			rquestparams.put("tranType", reqqueryorder.getTranName());
			rquestparams.put("param", data);
			
			//启动server 进行Post请求
			String returnres=QueryOrderService.queryorderservice.queryOrder(Utils.readProp("merKey"), rquestparams);
			logger.info("订单查询的返回数据为,returnres["+returnres+"]");
			
			ResponseQueryOrder respqueryorder=XmlObjMcUtil.converyToJavaBean(returnres, ResponseQueryOrder.class);
			boolean checksgin=QueryOrderService.queryorderservice.checkCallBackSgin(respqueryorder);
			if(checksgin){
				logger.info("验签成功");
				setAttr("RespQueryOrder", respqueryorder);
				render("/main/queryorder_success.html");
			}else{
				logger.info("验签失败");
			}
			
		} catch (JAXBException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.error("请求数据data转换失败:"+e);
		}	
	}
	
}
