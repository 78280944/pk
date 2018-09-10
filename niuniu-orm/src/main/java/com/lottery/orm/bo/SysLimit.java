package com.lottery.orm.bo;

import java.math.BigDecimal;

public class SysLimit {
    private Integer slid;

	private String gametype;

	private String gamename;

	private BigDecimal limited;

	private String offtype;

	private String state;

	private Integer ratio;

	public Integer getSlid() {
		return slid;
	}

	public void setSlid(Integer slid) {
		this.slid = slid;
	}

	public String getGametype() {
		return gametype;
	}

	public void setGametype(String gametype) {
		this.gametype = gametype == null ? null : gametype.trim();
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename == null ? null : gamename.trim();
	}

	public BigDecimal getLimited() {
		return limited;
	}

	public void setLimited(BigDecimal limited) {
		this.limited = limited;
	}

	public String getOfftype() {
		return offtype;
	}

	public void setOfftype(String offtype) {
		this.offtype = offtype == null ? null : offtype.trim();
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}

	public Integer getRatio() {
		return ratio;
	}

	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}

}