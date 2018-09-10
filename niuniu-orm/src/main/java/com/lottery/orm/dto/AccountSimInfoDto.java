package com.lottery.orm.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountSimInfoDto {
	@ApiModelProperty(value = "(流水号)用户ID", required = true)
	private Integer accountid;
	
	@ApiModelProperty(value = "别名", required = true)
	private String ausername;

	@ApiModelProperty(value = "登陆密码", required = true)
	private String password;

	@ApiModelProperty(value = "电话号码", required = true)
	private String phone;
	
	@ApiModelProperty(value = "微信号", required = true)
	private String webchat;

	@ApiModelProperty(value = "银行户名", required = true)
    private String bankid;

	@ApiModelProperty(value = "银行名称", required = true)
    private String bankname;

	@ApiModelProperty(value = "开户行", required = true)
    private String bankaddress;

	@ApiModelProperty(value = "银行账号", required = true)
    private String bankaccount;

	@ApiModelProperty(value = "银行编码，例如工商银行，1", required = true)
    private String bankno;
	
	@ApiModelProperty(value = "银行开户省份", required = true)
    private String bankloproname;
	
	@ApiModelProperty(value = "银行开户城市", required = true)
    private String banklocityname;
	
	@ApiModelProperty(value = "银行预留手机号码", required = true)
    private String phoneno;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebchat() {
		return webchat;
	}

	public void setWebchat(String webchat) {
		this.webchat = webchat;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
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

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getBankloproname() {
		return bankloproname;
	}

	public void setBankloproname(String bankloproname) {
		this.bankloproname = bankloproname;
	}

	public String getBanklocityname() {
		return banklocityname;
	}

	public void setBanklocityname(String banklocityname) {
		this.banklocityname = banklocityname;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	
}
