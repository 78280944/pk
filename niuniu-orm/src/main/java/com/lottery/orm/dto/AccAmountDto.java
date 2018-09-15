package com.lottery.orm.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccAmountDto {
	
	@ApiModelProperty(value = "编号", required = true)
	private Integer aaid;

	@ApiModelProperty(value = "用户名", required = true)
    private String username;
    
	@ApiModelProperty(value = "期次", required = true)
    private String lotteryterm;
    
	@ApiModelProperty(value = "时间", required = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date starttime;

	@ApiModelProperty(value = "代理佣金", required = true)
    private BigDecimal earns;

	@ApiModelProperty(value = "分享佣金", required = true)
    private BigDecimal gains;

	public Integer getAaid() {
		return aaid;
	}

	public BigDecimal getEarns() {
		return earns;
	}

	public void setEarns(BigDecimal earns) {
		this.earns = earns;
	}

	public BigDecimal getGains() {
		return gains;
	}

	public void setGains(BigDecimal gains) {
		this.gains = gains;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}

	public void setAaid(Integer aaid) {
		this.aaid = aaid;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

    
}
