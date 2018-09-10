package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UpdateAccountPerVo {
	
	@ApiModelProperty(value = "下级用户的id", required = true)
	private Integer accountid;
	
	@ApiModelProperty(value = "代理占成", required = true)
	private Double petcentage;
	
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

	public Double getPetcentage() {
		return petcentage;
	}

	public void setPetcentage(Double petcentage) {
		this.petcentage = petcentage;
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
