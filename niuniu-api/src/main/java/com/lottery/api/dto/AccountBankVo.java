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
	
	@ApiModelProperty(value = "开户行", required = true)
    private String bankaddress;
	
	@ApiModelProperty(value = "银行卡号", required = true)
    private String bankaccount;
	
	@ApiModelProperty(value = "开户行所在城市,例如长沙市", required = true)
    private String banklocityname;
	
	@ApiModelProperty(value = "手机号", required = true)
    private String phone;
	
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

	public String getBankaddress() {
		return bankaddress;
	}

	public void setBankaddress(String bankaddress) {
		this.bankaddress = bankaddress;
	}

	public String getBankaccount() {
		return bankaccount;
	}

	public void setBankaccount(String bankaccount) {
		this.bankaccount = bankaccount;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBanklocityname() {
		return banklocityname;
	}

	public void setBanklocityname(String banklocityname) {
		this.banklocityname = banklocityname;
	}


}
