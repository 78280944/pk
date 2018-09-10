package com.lottery.api.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UpdatePlayPassVo {
	
	@ApiModelProperty(value = "用户id", required = true)
	private int accountid;
	
	@ApiModelProperty(value = "密码", required = true)
	private String password;
	
	@ApiModelProperty(value = "状态", required = true)
	private String state;
	
	@ApiModelProperty(value = "代理账户id", required = true)
	private String supuserid;
	
	@ApiModelProperty(value = "ip", required = true)
	private String ip;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getSupuserid() {
		return supuserid;
	}

	public void setSupuserid(String supuserid) {
		this.supuserid = supuserid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
