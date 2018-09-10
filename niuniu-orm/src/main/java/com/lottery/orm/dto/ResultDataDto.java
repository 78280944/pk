package com.lottery.orm.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class ResultDataDto {
	@ApiModelProperty(value = "开奖ID", required = true)
	private Integer lgrid;
	
	@ApiModelProperty(value = "游戏期数", required = true)
	private String lotteryterm;
	
	@ApiModelProperty(value = "游戏结果", required = true)
	private String lotteryresult;

	public Integer getLgrid() {
		return lgrid;
	}

	public void setLgrid(Integer lgrid) {
		this.lgrid = lgrid;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}

	public String getLotteryresult() {
		return lotteryresult;
	}

	public void setLotteryresult(String lotteryresult) {
		this.lotteryresult = lotteryresult;
	}
	
	
}
