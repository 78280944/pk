package com.lottery.orm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class ResultDataDto {
	@ApiModelProperty(value = "开奖ID", required = true)
	private Integer lgrid;
	
	@ApiModelProperty(value = "游戏期数", required = true)
	private String lotteryterm;
	
	@ApiModelProperty(value = "游戏结果", required = true)
	private String lotteryresult;
	
	@ApiModelProperty(value = "游戏开始时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date starttime;

	@ApiModelProperty(value = "游戏开奖时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date opentime;
	
	@ApiModelProperty(value = "系统当前时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date systime;
	
	@ApiModelProperty(value = "剩余期数", required = true)
	private Integer dcount;
	
	@ApiModelProperty(value = "总期数", required = true)
	private Integer gcount;
	
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

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}


	public Date getOpentime() {
		return opentime;
	}

	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}

	public Integer getGcount() {
		return gcount;
	}

	public void setGcount(Integer gcount) {
		this.gcount = gcount;
	}

	public Integer getDcount() {
		return dcount;
	}

	public void setDcount(Integer dcount) {
		this.dcount = dcount;
	}

	public Date getSystime() {
		return systime;
	}

	public void setSystime(Date systime) {
		this.systime = systime;
	}
	
	
}
