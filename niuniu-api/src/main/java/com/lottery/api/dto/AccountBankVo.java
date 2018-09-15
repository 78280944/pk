package com.lottery.api.dto;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountBankVo {
	
	@ApiModelProperty(value = "用户ID", required = true)
	@NotNull(message = "用户ID不能为空")
	private Integer accountid;

	@ApiModelProperty(value = "用户名", required = true)
	@NotNull(message = "用户名不能为空")
    private String username;

	@ApiModelProperty(value = "持卡人", required = true)
    private String bankno;
	
	@ApiModelProperty(value = "身份证", required = true)
    private String bankid;
	
	@ApiModelProperty(value = "银行卡号", required = true)
    private String bankaccount;
	
	@ApiModelProperty(value = "手机号", required = true)
    private String phoneno;
	
	@ApiModelProperty(value = "IP", required = true)
    private String ip;

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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	
}
