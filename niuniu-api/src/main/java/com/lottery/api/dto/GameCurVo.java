package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class GameCurVo {
	
	@ApiModelProperty(value = "游戏大厅编号", required = true)
	private Integer sid;
	
	@ApiModelProperty(value = "游戏期次", required = true)
	private String lotteryterm;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}

	
}
