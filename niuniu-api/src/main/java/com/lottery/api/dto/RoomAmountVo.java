package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomAmountVo {
	
	@ApiModelProperty(value = "游戏房间号", required = true)
	private Integer rmid;
	
	@ApiModelProperty(value = "游戏期次", required = true)
    private String lotteryterm;

	public Integer getRmid() {
		return rmid;
	}

	public void setRmid(Integer rmid) {
		this.rmid = rmid;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}
	
    
    
}
