package com.jetpay.model.netpayment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="payment") 
public class ResponseNetPayMent{
	
	@XmlElement(name="tranName")
	private String tranName;
	@XmlElement(name="version")
	private String version;
	@XmlElement(name="merCode")
	private String merCode;
	@XmlElement(name="flowNo")
	private String flowNo;
	@XmlElement(name="orderNo")
	private String orderNo;
	@XmlElement(name="orderDate")
	private String orderDate;
	@XmlElement(name="ordAmt")
	private String ordAmt;
	@XmlElement(name="perFee")
	private String perFee;
	@XmlElement(name="currency")
	private String currency;
	@XmlElement(name="payType")
	private String payType;
	@XmlElement(name="paymentState")
	private String paymentState;
	@XmlElement(name="orderDealTime")
	private String orderDealTime;
	@XmlElement(name="workdate")
	private String workdate;
	@XmlElement(name="clearDate")
	private String clearDate;
	@XmlElement(name="validateField")
	private String validateField;
	@XmlElement(name="sign")
	private String sign;
	@XmlElement(name="errorCode")
	private String errorCode;
	@XmlElement(name="errorMessage")
	private String errorMessage;
	
	@XmlTransient
	public String getTranName() {
		return tranName;
	}
	public void setTranName(String tranName) {
		this.tranName = tranName;
	}
	@XmlTransient
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	@XmlTransient
	public String getMerCode() {
		return merCode;
	}
	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}
	@XmlTransient
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	@XmlTransient
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@XmlTransient
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	@XmlTransient
	public String getOrdAmt() {
		return ordAmt;
	}
	public void setOrdAmt(String ordAmt) {
		this.ordAmt = ordAmt;
	}
	@XmlTransient
	public String getPerFee() {
		return perFee;
	}
	public void setPerFee(String perFee) {
		this.perFee = perFee;
	}
	@XmlTransient
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	@XmlTransient
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	@XmlTransient
	public String getPaymentState() {
		return paymentState;
	}
	public void setPaymentState(String paymentState) {
		this.paymentState = paymentState;
	}
	@XmlTransient
	public String getOrderDealTime() {
		return orderDealTime;
	}
	public void setOrderDealTime(String orderDealTime) {
		this.orderDealTime = orderDealTime;
	}
	@XmlTransient
	public String getWorkdate() {
		return workdate;
	}
	public void setWorkdate(String workdate) {
		this.workdate = workdate;
	}
	@XmlTransient
	public String getClearDate() {
		return clearDate;
	}
	public void setClearDate(String clearDate){
		this.clearDate = clearDate;
	}
	@XmlTransient
	public String getValidateField(){
		return validateField;
	}
	public void setValidateField(String validateField) {
		this.validateField = validateField;
	}
	@XmlTransient
	public String getSign(){
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@XmlTransient
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	@XmlTransient
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
