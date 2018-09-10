package com.lottery.orm.bo;

import java.util.Date;

public class LotteryOrderResult {
    private Integer resultid;

	private Integer accountid;

	private Integer orderid;

	private Double winamount;

	private Double prizeamount;

	private Double returnamount;

	private Double feeamount;

	private Date inputtime;

	public Integer getResultid() {
		return resultid;
	}

	public void setResultid(Integer resultid) {
		this.resultid = resultid;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Double getWinamount() {
		return winamount;
	}

	public void setWinamount(Double winamount) {
		this.winamount = winamount;
	}

	public Double getPrizeamount() {
		return prizeamount;
	}

	public void setPrizeamount(Double prizeamount) {
		this.prizeamount = prizeamount;
	}

	public Double getReturnamount() {
		return returnamount;
	}

	public void setReturnamount(Double returnamount) {
		this.returnamount = returnamount;
	}

	public Double getFeeamount() {
		return feeamount;
	}

	public void setFeeamount(Double feeamount) {
		this.feeamount = feeamount;
	}

	public Date getInputtime() {
		return inputtime;
	}

	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}

}