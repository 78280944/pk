package com.lottery.orm.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomAmountDto {
	
	@ApiModelProperty(value = "排名", required = true)
	private Integer orderno;
	
	@ApiModelProperty(value = "客户ID", required = true)
	private Integer accountid;	
	
	@ApiModelProperty(value = "昵称", required = true)
	private String ausername;
	
	@ApiModelProperty(value = "收益", required = true)
	private BigDecimal lastamount;
	
	@ApiModelProperty(value = "庄闲", required = true)
	private String playoridle;

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getAusername() {
		return ausername;
	}

	public void setAusername(String ausername) {
		this.ausername = ausername;
	}

	public BigDecimal getLastamount() {
		return lastamount;
	}

	public void setLastamount(BigDecimal lastamount) {
		this.lastamount = lastamount;
	}

	public String getPlayoridle() {
		return playoridle;
	}

	public void setPlayoridle(String playoridle) {
		this.playoridle = playoridle;
	}
	
}
