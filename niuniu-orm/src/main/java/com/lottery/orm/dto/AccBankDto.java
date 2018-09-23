package com.lottery.orm.dto;

import com.lottery.orm.bo.AccountBankKey;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccBankDto extends AccountBankKey {
	
	@ApiModelProperty(value = "用户名", required = true)
	private String username;

	@ApiModelProperty(value = "持卡人", required = true)
	private String bankno;

	@ApiModelProperty(value = "开户行", required = true)
	private String bankaddress;

	@ApiModelProperty(value = "账号", required = true)
    private String bankaccount;

	@ApiModelProperty(value = "电话", required = true)
	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	
	
}
