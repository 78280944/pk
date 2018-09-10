package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UpdateAccountStateVo {
	
	@ApiModelProperty(value = "下级用户的id", required = true)
	private Integer accountid;
	
	@ApiModelProperty(value = "状态;0，冻结，1，正常", required = true)
	private String state;
	
	@ApiModelProperty(value = "当前用户ID", required = true)
	private Integer supaccountid;
	
	@ApiModelProperty(value = "ip", required = true)
	private String ip;

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getSupaccountid() {
		return supaccountid;
	}

	public void setSupaccountid(Integer supaccountid) {
		this.supaccountid = supaccountid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	
}
