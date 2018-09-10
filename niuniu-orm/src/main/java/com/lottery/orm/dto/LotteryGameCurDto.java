package com.lottery.orm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryGameCurDto {
	
	@ApiModelProperty(value = "游戏ID", required = true)
	private Integer sid;

	@ApiModelProperty(value = "上期游戏期次", required = true)
	private String lastlotteryterm;

	@ApiModelProperty(value = "上期游戏结果", required = true)
	private String lastlotteryresult;

	@ApiModelProperty(value = "当期游戏期次", required = true)
	private String lotteryterm;
	
	@ApiModelProperty(value = "游戏开始时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date starttime;

	@ApiModelProperty(value = "游戏封盘时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date overtime;

	@ApiModelProperty(value = "游戏开奖时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date opentime;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getLastlotteryterm() {
		return lastlotteryterm;
	}

	public void setLastlotteryterm(String lastlotteryterm) {
		this.lastlotteryterm = lastlotteryterm;
	}

	public String getLastlotteryresult() {
		return lastlotteryresult;
	}

	public void setLastlotteryresult(String lastlotteryresult) {
		this.lastlotteryresult = lastlotteryresult;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getOvertime() {
		return overtime;
	}

	public void setOvertime(Date overtime) {
		this.overtime = overtime;
	}

	public Date getOpentime() {
		return opentime;
	}

	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}

	
}
