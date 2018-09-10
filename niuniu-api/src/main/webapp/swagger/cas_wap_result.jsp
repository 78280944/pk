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
<%@ page import="com.kdpay.util.MD5" %>

<%
	 //request.setCharacterEncoding("UTF-8");
try {
	 System.out.println("----------- Payment system --> 异步通知URL，网关接口参数(notifyUrl) .....  ------>  wx_wap_notify.jsp");
	 		 //成功接收通知数据，返回success
	 		 System.out.println("success");
		     response.getWriter().print("ErrCode=0"); 
	
} catch (Exception e) {
	e.printStackTrace();
	System.out.println("处理支付异步请求异常:"+e);
}
	
%>


