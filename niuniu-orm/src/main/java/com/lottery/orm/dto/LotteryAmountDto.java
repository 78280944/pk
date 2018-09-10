package com.lottery.orm.dto;

import java.math.BigDecimal;


public class LotteryAmountDto {


	private Integer lgmid;
	
	private Integer accountid;
	
	private BigDecimal usermoney;
	
	private String playoridle;
	
	private BigDecimal orderamount;
	
	private String resultvalue;
	
	private Integer ascc;
	
	private Double ratio;
	
	private String state;
	
	private String type;

	private  BigDecimal lastamount;
	
	private Integer noid;
	
	
	public Integer getNoid() {
		return noid;
	}

	public void setNoid(Integer noid) {
		this.noid = noid;
	}

	public BigDecimal getLastamount() {
		return lastamount;
	}

	public void setLastamount(BigDecimal lastamount) {
		this.lastamount = lastamount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getLgmid() {
		return lgmid;
	}

	public void setLgmid(Integer lgmid) {
		this.lgmid = lgmid;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public BigDecimal getUsermoney() {
		return usermoney;
	}

	public void setUsermoney(BigDecimal usermoney) {
		this.usermoney = usermoney;
	}

	public String getPlayoridle() {
		return playoridle;
	}

	public void setPlayoridle(String playoridle) {
		this.playoridle = playoridle;
	}

	public BigDecimal getOrderamount() {
		return orderamount;
	}

	public void setOrderamount(BigDecimal orderamount) {
		this.orderamount = orderamount;
	}


	public String getResultvalue() {
		return resultvalue;
	}

	public void setResultvalue(String resultvalue) {
		this.resultvalue = resultvalue;
	}

	public Integer getAscc() {
		return ascc;
	}

	public void setAscc(Integer ascc) {
		this.ascc = ascc;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	
	
}
