package com.lottery.orm.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class OffsAccountDto {
	@ApiModelProperty(value = "(流水号)用户ID", required = true)
	private Integer accountid;
	
	@ApiModelProperty(value = "用户名", required = true)
	private String username;

	@ApiModelProperty(value = "别名", required = true)
	private String ausername;

	@ApiModelProperty(value = "登陆密码", required = true)
	private String password;

	@ApiModelProperty(value = "上线", required = true)
	private String supusername;
	
	@ApiModelProperty(value = "账户余额", required = true)
	private BigDecimal usermoney;
	
	@ApiModelProperty(value = "代理占成", required = true)
	private double percentage;

	@ApiModelProperty(value = "状态,0:冻结，1:正常", required = true)
	private String state;

	@ApiModelProperty(value = "管理账户", required = true)
	private Integer supuserid;

	@ApiModelProperty(value = "管理账户级别，0：超级管理员，1：一级代理，2：二级代理，3：三级代理,9：会员", required = true)
	private String level;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getAusername() {
		return ausername;
	}

	public void setAusername(String ausername) {
		this.ausername = ausername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSupusername() {
		return supusername;
	}

	public void setSupusername(String supusername) {
		this.supusername = supusername;
	}

	public BigDecimal getUsermoney() {
		return usermoney;
	}

	public void setUsermoney(BigDecimal usermoney) {
		this.usermoney = usermoney;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getSupuserid() {
		return supuserid;
	}

	public void setSupuserid(Integer supuserid) {
		this.supuserid = supuserid;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
