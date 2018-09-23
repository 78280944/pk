package com.lottery.orm.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryGameRound {
	
	@ApiModelProperty(value = "ID", required = true)
    private Integer lgrid;
	
	@ApiModelProperty(value = "游戏ID", required = true)
	private Integer sid;

	@ApiModelProperty(value = "游戏期次", required = true)
	private String lotteryterm;

	@ApiModelProperty(value = "游戏结果", required = true)
	private String lotteryresult;
	
	@ApiModelProperty(value = "游戏获奖号码", required = true)
	private String first;

	@ApiModelProperty(value = "游戏开始时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date starttime;

	@ApiModelProperty(value = "游戏封盘时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date overtime;

	@ApiModelProperty(value = "游戏开奖时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date opentime;

	@ApiModelProperty(value = "游戏结束时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date closetime;

	@ApiModelProperty(value = "游戏实际开奖时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date actopentime;

	@ApiModelProperty(value = "游戏实际结束时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date actclosetime;

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

	public Date getClosetime() {
		return closetime;
	}

	public void setClosetime(Date closetime) {
		this.closetime = closetime;
	}

	public Date getActopentime() {
		return actopentime;
	}

	public void setActopentime(Date actopentime) {
		this.actopentime = actopentime;
	}

	public Date getActclosetime() {
		return actclosetime;
	}

	public void setActclosetime(Date actclosetime) {
		this.actclosetime = actclosetime;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public Integer getDcount() {
		return dcount;
	}

	public void setDcount(Integer dcount) {
		this.dcount = dcount;
	}

	public Integer getGcount() {
		return gcount;
	}

	public void setGcount(Integer gcount) {
		this.gcount = gcount;
	}



	
}