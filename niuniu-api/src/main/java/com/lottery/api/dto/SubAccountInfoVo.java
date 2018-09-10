package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class SubAccountInfoVo extends BaseAccountInfoVo{
	
	@ApiModelProperty(value = "管理人id")
	private Integer accountid;
	
	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}	
	
	
}
