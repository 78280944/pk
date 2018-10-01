package com.jetpay.model.netpayment;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.jetpay.model.RequestBaseModel;

/**
 * @ClassName:     NetPayMent
 * @Description:   TODO(polypay 网上支付/充值netpayment实体类)
 * @author:        zdg
 * @date:          2017-7-11 下午04:13:28
 */
@XmlRootElement(name="payment")  
public class RequestNetPayMent{
	
	@XmlElement(name="tranName")
	private String tranName;
	@XmlElement(name="version")
	private String version;	
	@XmlElement(name="merCode")
	private String merCode;				
	@XmlElement(name="orderNo")
	private String orderNo;	
	@XmlElement(name="orderTime")
	private String orderTime;
	
	@XmlElement(name="payCode")
	private String payCode;
	@XmlElement(name="payType")
	private String payType;	
	
	@XmlElement(name="amount")
	private String amount;	
	@XmlElement(name="currency")
	private String currency;
	@XmlElement(name="productName")
	private String productName;
	@XmlElement(name="orderDesc")
	private String orderDesc;
	@XmlElement(name="returnURL")
	private String returnURL;
	@XmlElement(name="notifyURL")
	private String notifyURL;			
	
	@XmlElement(name="sign")
	private String sign;
	@XmlElement(name="reservedField1")
	private String reservedField1;
	@XmlElement(name="reservedField2")
	private String reservedField2;		
	
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	@XmlTransient
	public String getOrderTime(){
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	@XmlTransient
	public String getPayCode() {
		return payCode;
	}
	public void setPayCode(String payCode) {
		this.payCode = payCode;
	}
	@XmlTransient
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	@XmlTransient
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	@XmlTransient
	public String getReturnURL() {
		return returnURL;
	}
	public void setReturnURL(String returnURL){
		this.returnURL = returnURL;
	}
	@XmlTransient
	public String getNotifyURL() {
		return notifyURL;
	}
	public void setNotifyURL(String notifyURL) {
		this.notifyURL = notifyURL;
	}
	@XmlTransient
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
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
}
