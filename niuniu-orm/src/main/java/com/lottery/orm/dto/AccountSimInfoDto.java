package com.lottery.orm.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountSimInfoDto {
	
	@ApiModelProperty(value = "(流水号)用户ID", required = true)
	private Integer accountid;
	
	@ApiModelProperty(value = "用户名", required = true)
	private String username;

	@ApiModelProperty(value = "登陆密码", required = true)
	private String password;

	@ApiModelProperty(value = "账户余额", required = true)
	private BigDecimal usermoney;
	
	@ApiModelProperty(value = "冻结余额", required = true)
	private BigDecimal percentage;
	
	@ApiModelProperty(value = "上级用户ID", required = true)
	private String supuserid;

	@ApiModelProperty(value = "持卡人", required = true)
    private String bankno;
	
	@ApiModelProperty(value = "身份证", required = true)
    private String bankid;

	@ApiModelProperty(value = "银行账号", required = true)
    private String bankaccount;
	
	@ApiModelProperty(value = "银行预留手机号码", required = true)
    private String phoneno;
	
	@ApiModelProperty(value = "登录记录流水号", required = true)
	private String recordid;

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSupuserid() {
		return supuserid;
	}

	public void setSupuserid(String supuserid) {
		this.supuserid = supuserid;
	}

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public BigDecimal getUsermoney() {
		return usermoney;
	}

	public void setUsermoney(BigDecimal usermoney) {
		this.usermoney = usermoney;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	
}
