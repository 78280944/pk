package com.lottery.api.dto;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryGameDetailVO {
	@ApiModelProperty(value = "订单号", required = true)
	@NotNull(message = "订单号不能为空")
	private Integer lgmid ;

	public Integer getLgmid() {
		return lgmid;
	}

	public void setLgmid(Integer lgmid) {
		this.lgmid = lgmid;
	}
	
	
}
