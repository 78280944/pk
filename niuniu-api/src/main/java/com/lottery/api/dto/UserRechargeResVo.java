package com.lottery.api.dto;


import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserRechargeResVo {
	
	@ApiModelProperty(value = "订单号", required = true)
	private String orderNo;
	
	@ApiModelProperty(value = "交易金额", required = true)
	private String transAmt;
	
	@ApiModelProperty(value = "支付平台订单号", required = true)
	private String payNo;
	
	@ApiModelProperty(value = "支付平台返回代码", required = true)
	private String respCode;

	@ApiModelProperty(value = "支付平台返回消息描述", required = true)
	private String respDesc;
	
	@ApiModelProperty(value = "订单日期", required = true)
	private String orderDate;	
		

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespDesc() {
		return respDesc;
	}

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


}
