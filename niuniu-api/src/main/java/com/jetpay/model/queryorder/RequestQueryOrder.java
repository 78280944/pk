package com.jetpay.model.queryorder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 * @ClassName:     RequestQueryOrder
 * @Description:   TODO(订单查询请求类)
 * @author:        polypay
 * @date:          2017-7-13 下午05:51:09
 */
@XmlRootElement(name="queryOrder")  
public class RequestQueryOrder {
	
	@XmlElement(name="tranName")
	private String tranName;
	@XmlElement(name="version")
	private String version;
	@XmlElement(name="orderNo")
	private String orderNo;
	@XmlElement(name="merCode")
	private String merCode;
	@XmlElement(name="orderType")
	private String orderType;
	@XmlElement(name="reservedField1")
	private String reservedField1;
	@XmlElement(name="reservedField2")
	private String reservedField2;
	@XmlElement(name="sign")
	private String sign;
	
	@XmlTransient
	public String getTranName(){
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@XmlTransient
	public String getMerCode() {
		return merCode;
	}
	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}
	@XmlTransient
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
