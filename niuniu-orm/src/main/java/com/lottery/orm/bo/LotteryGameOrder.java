package com.lottery.orm.bo;

import java.math.BigDecimal;
import java.util.Date;

public class LotteryGameOrder {
    private Integer lgmid;

	private Integer sid;

	private Integer accountid;

	private Integer rmid;

	private Integer ltdid;

	private Integer noid;

	private String playoridle;

	private String lotteryterm;

	private BigDecimal orderamount;

	private Date ordertime;

	private Date opentime;

	private String result;

	private BigDecimal lastamount;

	public Integer getLgmid() {
		return lgmid;
	}

	public void setLgmid(Integer lgmid) {
		this.lgmid = lgmid;
	}

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

	public Integer getRmid() {
		return rmid;
	}

	public void setRmid(Integer rmid) {
		this.rmid = rmid;
	}

	public Integer getLtdid() {
		return ltdid;
	}

	public void setLtdid(Integer ltdid) {
		this.ltdid = ltdid;
	}

	public Integer getNoid() {
		return noid;
	}

	public void setNoid(Integer noid) {
		this.noid = noid;
	}

	public String getPlayoridle() {
		return playoridle;
	}

	public void setPlayoridle(String playoridle) {
		this.playoridle = playoridle;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm;
	}

	public BigDecimal getOrderamount() {
		return orderamount;
	}

	public void setOrderamount(BigDecimal orderamount) {
		this.orderamount = orderamount;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public Date getOpentime() {
		return opentime;
	}

	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public BigDecimal getLastamount() {
		return lastamount;
	}

	public void setLastamount(BigDecimal lastamount) {
		this.lastamount = lastamount;
	}

	
}