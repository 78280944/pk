package com.lottery.api.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class OffAccountInfoVo extends BaseAccountInfoVo{
	

    @ApiModelProperty(value = "代理占成")
    private Double percentage;

    @ApiModelProperty(value = "邀请码")
    private String code;
    
    @ApiModelProperty(value = "管理人id")
    private Integer accountid;

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}	
	   
}
