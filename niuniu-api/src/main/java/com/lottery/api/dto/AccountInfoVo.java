package com.lottery.api.dto;

import javax.validation.constraints.Min;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountInfoVo  extends PageParamVo{
	
	@ApiModelProperty(value = "用户ID", required = true)
	@Min(value = 0, message = "ID格式不正确")
    private Integer accountid;

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	} 



}