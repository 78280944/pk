package com.lottery.orm.bo;

import java.math.BigDecimal;
import java.util.Date;

public class AccountAmount extends AccountAmountKey {
	private BigDecimal loss;

	private BigDecimal earns;

	private BigDecimal gains;

	private BigDecimal cfee;

	private BigDecimal profits;

	private Date starttime;

	private Date overtime;

	public BigDecimal getLoss() {
		return loss;
	}

	public void setLoss(BigDecimal loss) {
		this.loss = loss;
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

	public BigDecimal getCfee() {
		return cfee;
	}

	public void setCfee(BigDecimal cfee) {
		this.cfee = cfee;
	}

	public BigDecimal getProfits() {
		return profits;
	}

	public void setProfits(BigDecimal profits) {
		this.profits = profits;
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

	
}