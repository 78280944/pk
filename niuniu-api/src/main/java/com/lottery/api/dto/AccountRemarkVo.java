package com.lottery.api.dto;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountRemarkVo {
	
	@ApiModelProperty(value = "用户ID", required = true)
	@NotNull(message = "用户ID不能为空")
	private Integer accountid;

	@ApiModelProperty(value = "用户名", required = true)
	@NotNull(message = "用户名不能为空")
    private String username;

	@ApiModelProperty(value = "意见，不超过500字", required = true)
    private String remark;
	
	@ApiModelProperty(value = "IP", required = true)
    private String ip;

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	
	
}
