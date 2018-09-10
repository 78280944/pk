package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UpdateAccountRatioVo {
	
	@ApiModelProperty(value = "用户id", required = true)
	private int userid;
	
	@ApiModelProperty(value = "洗码比", required = true)
	private Double ratio;
	
	@ApiModelProperty(value = "状态", required = true)
	private String state;
	
	@ApiModelProperty(value = "代理账户名称", required = true)
	private String supusername;
	
	@ApiModelProperty(value = "代理账户类型", required = true)
	private int offtype;
	
	@ApiModelProperty(value = "ip", required = true)
	private String ip;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSupusername() {
		return supusername;
	}

	public void setSupusername(String supusername) {
		this.supusername = supusername;
	}

	public int getOfftype() {
		return offtype;
	}

	public void setOfftype(int offtype) {
		this.offtype = offtype;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
