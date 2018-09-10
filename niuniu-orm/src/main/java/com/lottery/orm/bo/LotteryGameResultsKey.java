package com.lottery.orm.bo;

public class LotteryGameResultsKey {
    private Integer lgrsid;

	private String lotteryterm;
	
	private String type;

	private Integer sid;

	public Integer getLgrsid() {
		return lgrsid;
	}

	public void setLgrsid(Integer lgrsid) {
		this.lgrsid = lgrsid;
	}	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLotteryterm() {
		return lotteryterm;
	}

	public void setLotteryterm(String lotteryterm) {
		this.lotteryterm = lotteryterm == null ? null : lotteryterm.trim();
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	
}