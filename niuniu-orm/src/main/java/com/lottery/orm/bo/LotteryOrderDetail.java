package com.lottery.orm.bo;

public class LotteryOrderDetail extends LotteryOrderDetailKey {
    private Double itemscale;

	private Double itembonus;

	private Double detailamount;

	private Double prizeamount;

	private Double actualamount;

	private Double returnamount;

	public Double getItemscale() {
		return itemscale;
	}

	public void setItemscale(Double itemscale) {
		this.itemscale = itemscale;
	}

	public Double getItembonus() {
		return itembonus;
	}

	public void setItembonus(Double itembonus) {
		this.itembonus = itembonus;
	}

	public Double getDetailamount() {
		return detailamount;
	}

	public void setDetailamount(Double detailamount) {
		this.detailamount = detailamount;
	}

	public Double getPrizeamount() {
		return prizeamount;
	}

	public void setPrizeamount(Double prizeamount) {
		this.prizeamount = prizeamount;
	}

	public Double getActualamount() {
		return actualamount;
	}

	public void setActualamount(Double actualamount) {
		this.actualamount = actualamount;
	}

	public Double getReturnamount() {
		return returnamount;
	}

	public void setReturnamount(Double returnamount) {
		this.returnamount = returnamount;
	}

}