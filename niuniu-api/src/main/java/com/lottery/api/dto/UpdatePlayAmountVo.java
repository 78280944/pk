package com.lottery.api.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UpdatePlayAmountVo {
	
	@ApiModelProperty(value = "用户id", required = true)
	private int userid;
	
	@ApiModelProperty(value = "账号id", required = true)
	private int accountid;
	
	@ApiModelProperty(value = "剩余点数", required = true)
	private BigDecimal accountamount;
	
	@ApiModelProperty(value = "状态", required = true)
	private String state;
	
	@ApiModelProperty(value = "代理账户名称", required = true)
	private String supusername;
	
	@ApiModelProperty(value = "代理账户类型", required = true)
	private int offtype;
	
	@ApiModelProperty(value = "ip", required = true)
	private String ip;


	public int getOfftype() {
		return offtype;
	}

	public void setOfftype(int offtype) {
		this.offtype = offtype;
	}

	public String getSupusername() {
		return supusername;
	}

	public void setSupusername(String supusername) {
		this.supusername = supusername;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public BigDecimal getAccountamount() {
		return accountamount;
	}

	public void setAccountamount(BigDecimal accountamount) {
		this.accountamount = accountamount;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
