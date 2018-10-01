package com.jetpay.model;

public class ResponseBaseModel {
										/*名称*/					/*是否必填（Y）*/	 /*最大长度*/	
	private String tranName;			//交易名称					Y						
	private String version;				//版本号						Y
	private String merCode;				//商户号						Y					15
	private String flowNo;				//系统流水号
	private double ordAmt;				//订单金额					Y					N							
	private String returnURL;			//商户URL					N
	private String notifyURL;			//商户后台通知URL			Y
	private String errorCode;			//错误代码					N
	private String errorMessage;		//错误信息					N
	private String reservedField1;		//预留字段1					N	
	private String reservedField2;		//预留字段2					N
	private String sgin;				//签名值	
	
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
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	public double getOrdAmt() {
		return ordAmt;
	}
	public void setOrdAmt(double ordAmt) {
		this.ordAmt = ordAmt;
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
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
