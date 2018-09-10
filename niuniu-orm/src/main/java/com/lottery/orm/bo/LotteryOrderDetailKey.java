package com.lottery.orm.bo;

public class LotteryOrderDetailKey {
    private Integer orderid;

	private String itemno;

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getItemno() {
		return itemno;
	}

	public void setItemno(String itemno) {
		this.itemno = itemno == null ? null : itemno.trim();
	}

}