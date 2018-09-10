package com.lottery.api.dto;


import com.wordnik.swagger.annotations.ApiModelProperty;

public class CurResultParamVo extends PageParamVo {
	
	@ApiModelProperty(value = "05,本期;", required = true)
	private String time;
	
	@ApiModelProperty(value = "游戏号", required = true)
	private Integer sid;
	
	@ApiModelProperty(value = "用户号", required = true)
	private Integer accountid;

	
	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	
}

