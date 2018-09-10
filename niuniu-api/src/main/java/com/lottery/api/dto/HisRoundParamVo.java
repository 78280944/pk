package com.lottery.api.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class HisRoundParamVo extends PageParamVo{
	@ApiModelProperty(value = "游戏类型", required = true)
    @NotBlank(message = "游戏类型不能为空")
    private String lotteryType;

	public String getLotteryType() {
		return lotteryType;
	}

	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}
	
}