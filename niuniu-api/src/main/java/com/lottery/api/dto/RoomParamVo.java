package com.lottery.api.dto;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RoomParamVo extends PageParamVo {

	@ApiModelProperty(value = "开始时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startDate;
	
	@ApiModelProperty(value = "结束时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endDate;
	
	@ApiModelProperty(value = "01,本日;02,上周;03,本周;04,上期;05,本期;", required = true)
	private String time;
	
	@ApiModelProperty(value = "游戏号", required = true)
	private Integer sid;
	
	@ApiModelProperty(value = "房间号", required = true)
	private Integer rmid;
	
	@ApiModelProperty(value = "用户id", required = true)
	private Integer accountid;

	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getRmid() {
		return rmid;
	}

	public void setRmid(Integer rmid) {
		this.rmid = rmid;
	}
	
	
}
