package com.lottery.api.dto;


import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class CashOrderVo {
	
	@ApiModelProperty(value = "取现订单ID", required = true)
    private Integer arid;
	
	@ApiModelProperty(value = "操作ID", required = true)
    private String opuserid;
	
	public Integer getArid() {
		return arid;
	}

	public void setArid(Integer arid) {
		this.arid = arid;
	}

	public String getOpuserid() {
		return opuserid;
	}

	public void setOpuserid(String opuserid) {
		this.opuserid = opuserid;
	}

	
}
