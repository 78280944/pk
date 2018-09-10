package com.lottery.api.dto;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountRecordVo {
	@ApiModelProperty(value = "用户ID", required = true)
	@NotNull(message = "用户ID不能为空")
	protected Integer accountid ;
	
	@ApiModelProperty(value = "IP", required = true)
	private String ip;
	
	@ApiModelProperty(value = "登录记录流水号", required = true)
	private String recordid;

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	
	
}
