package com.jetpay.model;

public class RequestBaseModel {
	
	private String tranName;			//交易名称					Y						
	private String version;				//版本号						Y
	private String merCode;				//商户号						Y					15
	private double amount;				//订单金额					Y
	private String orderDesc;			//订单描述					N							
	private String returnURL;			//商户URL					N
	private String notifyURL;			//商户后台通知URL			Y
	private String reservedField1;		//预留字段1					N	
	private String reservedField2;		//预留字段2					N
	private String sgin;				//签名值						Y
	
	public String getTranName() {
		return tranName;
	}
	public void setTranName(String tranName) {
		this.tranName = tranName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMerCode() {
		return merCode;
	}
	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public String getReturnURL() {
		return returnURL;
	}
	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}
	public String getNotifyURL() {
		return notifyURL;
	}
	public void setNotifyURL(String notifyURL) {
		this.notifyURL = notifyURL;
	}
	public String getReservedField1() {
		return reservedField1;
	}
	public void setReservedField1(String reservedField1) {
		this.reservedField1 = reservedField1;
	}
	public String getReservedField2() {
		return reservedField2;
	}
	public void setReservedField2(String reservedField2) {
		this.reservedField2 = reservedField2;
	}
	public String getSgin() {
		return sgin;
	}
	public void setSgin(String sgin) {
		this.sgin = sgin;
	}
	
}
