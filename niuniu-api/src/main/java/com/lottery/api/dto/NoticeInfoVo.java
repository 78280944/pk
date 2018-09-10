package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class NoticeInfoVo {
	
	@ApiModelProperty(value = "公告标题", required = true)
	private String title;
	
	@ApiModelProperty(value = "公告内容", required = true)
	private String notice;
	
	@ApiModelProperty(value = "公告类型，0：代理公告；1，玩家公告", required = true)
	private String stype;
	
	@ApiModelProperty(value = "代理账户名称", required = true)
	private String supusername;
	
	@ApiModelProperty(value = "代理账户类型", required = true)
	private int offtype;
	
	@ApiModelProperty(value = "ip", required = true)
	private String ip;
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
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
