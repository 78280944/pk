package com.lottery.orm.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserRechargeResDto {
	
	@ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    @Min(value=0, message = "用户ID格式不正确")
    private Integer accountId;
	
	@ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;
	
	@ApiModelProperty(value = "订单号,1053，必须传参", required = true)
    @NotNull(message = "订单号不能为空")
    private String orderNo;
	
	@ApiModelProperty(value = "请求编号，1053，必须传参", required = true)
	private String requestNo;
	
	@ApiModelProperty(value = "页面通知地址，1053，必须传参", required = true)
	private String returnUrl;
	
	@ApiModelProperty(value = "异步通知地址，1053，必须传参", required = true)
	private String notifyUrl;
	
	@ApiModelProperty(value = "商户号，1053，必须传参", required = true)
	private String merNo;
	
	@ApiModelProperty(value = "支付链接，1053，支付网关", required = true)
	private String mwebUrl;
	
	@ApiModelProperty(value = "交易类型，1053，必须传参", required = true)
	private String transId;
	
	@ApiModelProperty(value = "版本号，1053，必须传参", required = true)
	private String version;
	
	@ApiModelProperty(value = "验签字段，1053，必须传参", required = true)
	private String signature;
	
	@ApiModelProperty(value = "订单日期，格式yyyyMMdd，1053，必须传参", required = true)
    private String orderDate;
	
	@ApiModelProperty(value = "产品编号，微信1205，1053，必须传参", required = true)
    @NotNull(message = "产品编号不能为空")
	private String productId;
	
	@ApiModelProperty(value = "交易金额，单位为分，1元传100，1053，必须传参", required = true)
    @NotNull(message = "交易金额不能为空")
	private Double transAmt;
	
	@ApiModelProperty(value = "商品名称，1053，必须传参", required = true)
    @NotNull(message = "商品名称不能为空")
	private String commodityName;
	
	@ApiModelProperty(value = "银行卡类，1053，必须传参", required = true)
    @NotNull(message = "银行卡类1053，默认")
	private String cardType;
	
	@ApiModelProperty(value = "银行简称，1053，必须传参", required = true)
    @NotNull(message = "银行简称1053，默认")
	private String bankCode;
	
	@ApiModelProperty(value = "交易备注，1053，必须传参", required = true)
    @NotNull(message = "银行简称1053，默认")
	private String remark;
	
	@ApiModelProperty(value = "扩展参数，1053，必须传参", required = true)
    @NotNull(message = "扩展参数1053，默认")
	private String extendField;
	
	@ApiModelProperty(value = "加密类型，1053，必须传参", required = true)
    @NotNull(message = "加密参数1053，默认")
	private String keyType;

	@ApiModelProperty(value = "IP", required = true)
    private String orderIp;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getMwebUrl() {
		return mwebUrl;
	}

	public void setMwebUrl(String mwebUrl) {
		this.mwebUrl = mwebUrl;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(Double transAmt) {
		this.transAmt = transAmt;
	}

	public String getOrderIp() {
		return orderIp;
	}

	public void setOrderIp(String orderIp) {
		this.orderIp = orderIp;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExtendField() {
		return extendField;
	}

	public void setExtendField(String extendField) {
		this.extendField = extendField;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

}
