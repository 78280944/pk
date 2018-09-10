<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.http.util.EntityUtils" %>
<%@ page import="com.colotnet.util.ConfigUtils" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="org.apache.http.entity.StringEntity" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="com.colotnet.util.SignUtils" %>

<%
	 //request.setCharacterEncoding("UTF-8");
try {
	 System.out.println("----------- Payment system --> 异步通知URL，网关接口参数(notifyUrl) .....  ------>  wx_wap_notify.jsp");
	 byte[] bytes = new byte[1024 * 1024];  
     InputStream is = request.getInputStream();  
     int nRead = 1;  
     int nTotalRead = 0;  
     while (nRead > 0) {  
         nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);  
         if (nRead > 0)  
             nTotalRead = nTotalRead + nRead;  
     }  
     String notifyStr = new String(bytes, 0, nTotalRead, "UTF-8"); 
    // notifyStr = "{\"accNo\":\"123456\",\"orderDate\":\"20171223\",\"orderNo\":\"20171223131135\",\"payNo\":\"123456\",\"respCode\":\"1001\",\"respDesc\":\"??\",\"token\":\"123456\",\"transAmt\":\"1000\"}";
    //notifyStr = "{\"respCode\":\"0000\",\"extendField\":\"\",\"remark\":\"\",\"orderDate\":\"20171226\",\"respDesc\":\"交易成功\",\"transAmt\":\"100\",\"productId\":\"1205\",\"payNo\":\"220000000415\",\"orderNo\":\"20171226165109\",\"transId\":\"\",\"signature\":\"fDgwG5GNbIsXISseGHbDicT9oh/QwwmKpvDOdQQ68WP3u3gFkILQbR7jNs5AUzWu1gpO4X/HdP1jUu6Z3JC4oeqbZ4sjIScgMnHOWXmEC81j15YBCaYMlT4w5zvTMlk3lxOqzG4eTx4dPKsNUemU1IayooY7mxqGo4TTGr75msvPO/yQPsFQfFZ+1xIoQIZ6P5gWNk30F99yuYZFEVY6YRv/TpcK3Tq/qIPbSCI2CywqNviw0z7p4sUPUioBowzUVUpxPT4FNHkzd6JjnN/94HZ9I7fuJRDg0pX/HMtOGRg11s939RA4c6QSrwotQwKjaMWVsFEHe8ShGShvZLRbUQ==\",\"merNo\":\"850610050942302\"}";
 
    System.out.println("通知内容:"+notifyStr);
     //进行验签
     boolean signFlag = SignUtils.verferSignData(notifyStr);
     Map mapTypes = JSON.parseObject(notifyStr);
     Map<String, Object> data = new HashMap<String, Object>();
	 if (signFlag) {
		 System.out.println("验签成功");
         //验签成功后解析返回的字段
     
         for (Object obj : mapTypes.keySet()){  
             if((!obj.toString().equals("signature"))){
             	System.out.println("通知字段-"+obj+":"+mapTypes.get(obj).toString());
             }
         }  
	}
	    System.out.println("----------------------------- 异步通知   end ---------------------------------------------");
	    DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost postMethod = new HttpPost(ConfigUtils.getProperty("resultUrl"));
        JSONObject json = new JSONObject(mapTypes);
        StringEntity se = new StringEntity(json.toString());
        se.setContentEncoding("UTF-8");
        se.setContentType("application/json");//发送json数据需要设置contentType
        //post.setEntity(s);
        postMethod.setEntity(se);
        HttpResponse resp = httpClient.execute(postMethod);
        String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
        System.out.println("返回结果："+str);
        JSONObject respJSONObject = JSON.parseObject(str);
        String returnStr = respJSONObject.getString("data");
	 	if (returnStr.equals("success")){
	 		 //成功接收通知数据，返回success
	 		 System.out.println("success");
		     response.getWriter().print("success"); 
	
	 }
} catch (Exception e) {
	e.printStackTrace();
	System.out.println("处理支付异步请求异常:"+e);
}
	
%>

