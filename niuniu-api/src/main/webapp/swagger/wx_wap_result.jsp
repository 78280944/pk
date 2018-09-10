<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.*" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
   	<body>
    <div>
    	<h2 align="center">商户指定的URL(页面)  即，网关参数(returnUrl)</h2>
    	<label>支付结果(respCode): <%=respCode%></label><br/>
    	<label>结果描述(respDesc): <%=respDesc%></label><br/>
    	<label>请求流水号(requestNo): <%=requestNo%></label><br/>
    	<label>产品ID(productId): <%=productId%></label><br/>
    	<label>交易ID(transId): <%=transId%></label><br/>
    	<label>商户号(merNo): <%=merNo%></label><br/>
    	<label>支付单号(payNo): <%=payNo%></label><br/>
    	<label>订单号(orderNo): <%=orderNo%></label><br/>
    	<label>交易金额(amount): <%=transAmt%></label><br/>
    	<label>交易日期(orderDate): <%=orderDate%></label><br/>
    	<label>签名内容(signature): <%=signature%></label>
    	
    	<label>
    		跳转地址：<br/>
    		<%=mwebUrl %>
    	</label>
    </div>
    
     <%
	 	System.out.println("----------- 支付网关  -> 商户页面[returnUrl]同步显示 ........ ------>  wx_wap_result.jsp");
	    Enumeration<?> temp = request.getParameterNames();
	    if (null != temp) {
	    	 while (temp.hasMoreElements()) {
		         String key = (String) temp.nextElement();
		         String value = request.getParameter(key);
		         System.out.println("page show -> " + key + ": " + value);
	    	 }
	    }
	    System.out.println("-------------------------------跳转到商户页面   end -------------------------------------------");
	%>
	</body>
</html>