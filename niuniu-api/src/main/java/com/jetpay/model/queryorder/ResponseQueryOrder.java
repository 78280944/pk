package com.jetpay.model.queryorder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 * 
 * @ClassName:     ResponseQueryOrder
 * @Description:   TODO(订单查询响应model)
 * @author:        zdg
 * @date:          2017-7-13 下午05:56:16
 */
@XmlRootElement(name="queryOrder")  
public class ResponseQueryOrder {
	
	@XmlElement(name="tranName")
	private String tranName;
	@XmlElement(name="version")
	private String version;
	@XmlElement(name="tranStateCode")
	private String tranStateCode;
	@XmlElement(name="merCode")
	private String merCode;
	@XmlElement(name="flowNo")
	private String flowNo;
	@XmlElement(name="orderType")
	private String orderType;
	@XmlElement(name="orderNo")
	private String orderNo;
	@XmlElement(name="orderDate")
	private String orderDate;
	@XmlElement(name="amount")
	private String amount;
	@XmlElement(name="currency")
	private String currency;
	@XmlElement(name="orderDealTime")
	private String orderDealTime;
	@XmlElement(name="workdate")
	private String workdate;
	@XmlElement(name="clearDate")
	private String clearDate;
	@XmlElement(name="errorCode")
	private String errorCode;
	@XmlElement(name="errorMessage")
	private String errorMessage;
	@XmlElement(name="reservedField1")
	private String reservedField1;
	@XmlElement(name="reservedField2")
	private String reservedField2;
	@XmlElement(name="sign")
	private String sign;
	
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
	public String getTranStateCode() {
		return tranStateCode;
	}
	public void setTranStateCode(String tranStateCode) {
		this.tranStateCode = tranStateCode;
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
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@XmlTransient
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
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
	public void setClearDate(String clearDate) {
		this.clearDate = clearDate;
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
	@XmlTransient
	public String getReservedField1() {
		return reservedField1;
	}
	public void setReservedField1(String reservedField1) {
		this.reservedField1 = reservedField1;
	}
	@XmlTransient
	public String getReservedField2() {
		return reservedField2;
	}
	public void setReservedField2(String reservedField2) {
		this.reservedField2 = reservedField2;
	}
	@XmlTransient
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
