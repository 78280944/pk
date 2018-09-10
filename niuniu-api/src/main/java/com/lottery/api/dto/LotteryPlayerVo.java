package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryPlayerVo {
	
	@ApiModelProperty(value = "游戏期次", required = true)
	private String lotteryterm;
	
	@ApiModelProperty(value = "游戏号", required = true)
	private Integer sid;
	
	@ApiModelProperty(value = "用户号", required = true)
	private Integer accountid;
}
