package com.jetpay.service.queryorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.jetpay.config.JetPayConfig;
import com.jetpay.model.queryorder.ResponseQueryOrder;
import com.jetpay.utils.MapJavaObjectConverrter;
import com.jetpay.utils.SecurityUtil;
/**
 * @ClassName:     QueryOrderService
 * @Description:   TODO(订单查询serivce)
 * @author:        zdg
 * @date:          2017-7-14 下午05:46:13
 */
public class QueryOrderService {

	private static Logger logger=Logger.getLogger(QueryOrderService.class);
	public static QueryOrderService queryorderservice=new QueryOrderService();
	
	/**
	 * @Title: queryOrder
	 * @Description: TODO(订单查询请求类)
	 * @param url
	 * @param params
	 * @return String
	 */
	public String queryOrder(String url,Map params){
		
		System.out.println("url"+url);
		String result=null;
		
		PostMethod postMethod=new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		List list=new ArrayList();
		
		if(!params.isEmpty()){	
			NameValuePair[] data=new NameValuePair[params.size()];
			Object[] keys=params.keySet().toArray();
			for(int i=0;i<keys.length;i++){
				String value=(String)params.get(keys[i]);
				data[i]=new NameValuePair((String)keys[i],value);
			}
			postMethod.setRequestBody(data);
			
		}
	
		HttpClient client=new HttpClient();
		int statucsCode;
		try {
			statucsCode = client.executeMethod(postMethod);
			if(statucsCode!=HttpStatus.SC_OK){
				logger.error("请求状态不为成功statusCode["+ postMethod.getStatusLine() + "],url[" + url + "]");
			}else {
				result = postMethod.getResponseBodyAsString();
				if (logger.isDebugEnabled())
					logger.debug("请求成功result[" + result + "]");
			}
		} catch (HttpException e){
			logger.error("请检查请求协议", e);
			throw new RuntimeException("请检查请求协议", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(String.format("网络异常,已经重试了[%s]次", 3), e);
		} catch(Exception e){
			logger.error("系统异常", e);
			throw new RuntimeException("系统异常", e);
		}finally{
			postMethod.releaseConnection();
		}
		
		return result;
	}
	
	/**
	 * @Title: checkCallBackSgin
	 * @Description: TODO(验证签名成功)
	 * @param rpqueryorder
	 * @return boolean
	*/
	public boolean checkCallBackSgin(ResponseQueryOrder rpqueryorder){
		
		String sgin=rpqueryorder.getSign();
		//返回签名不需要参与排序和生成签名原串
		rpqueryorder.setSign("");
		
		//把rpqueryorder装换成Map<String,String>,用于下面首字母排序
		Map<String,String> params=MapJavaObjectConverrter.objectToMapString(rpqueryorder, true);
		
		//转换成签名原串
		String paramsStr=MapJavaObjectConverrter.mapStringKeySortToLinkString(params, true);
		logger.info("pp_server响应的签名原串："+paramsStr);
		//生成签名
		String md5hexSign=SecurityUtil.MD5Hex(paramsStr+ "&key="+JetPayConfig.props.get("merKey").toString());
		logger.info("pp_server响应的签名原串生成的的签名 QueryOrder Sgin："+md5hexSign);
		//验证签名
		if(sgin.equals(md5hexSign)){
			return true;
		}else{
			logger.error("验签失败，请仔细检查签名原串和生成签名方法");
			return false;
		}	
	}
	
	
}
