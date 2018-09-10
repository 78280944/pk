package com.lottery.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomOrderVo {

	@ApiModelProperty(value = "游戏期次", required = true)
	private String lotteryterm;
	
	@ApiModelProperty(value = "游戏号", required = true)
	private Integer sid;
	
	@ApiModelProperty(value = "房间号", required = true)
	private Integer rmid;
	
	@ApiModelProperty(value = "用户号", required = true)
	private Integer accountid;

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getRmid() {
		return rmid;
	}

	public void setRmid(Integer rmid) {
		this.rmid = rmid;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	
	
}
