package com.lottery.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserCashVo {
	
	@ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    @Min(value=0, message = "用户ID格式不正确")
    private Integer accountId;

	@ApiModelProperty(value = "提现金额", required = true)
    @NotNull(message = "提现金额不能为空")
    private Double transAmt;
	
	@ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;
	
	@ApiModelProperty(value = "开户用户名", required = true)
    @NotNull(message = "开户用户名不能为空")
    private String bankId;

	
	@ApiModelProperty(value = "银行名称", required = true)
    @NotNull(message = "银行名称不能为空")
    private String bankName;

	@ApiModelProperty(value = "银行账户", required = true)
    @NotNull(message = "银行账户不能为空")
    private String bankAccount;

	@ApiModelProperty(value = "开户行/地址", required = true)
    @NotNull(message = "开户行/地址不能为空")
    private String bankAddress;
	
	@ApiModelProperty(value = "银行编码,例如工商银行，1",required = true)
    @NotNull(message = "银行编码不能为空")
	private String bankNo;

	@ApiModelProperty(value = "银行卡开户省份,福建省，上海市", required = true)
    @NotNull(message = "银行卡开户省份不能为空")
	private String bankloproname;
	
	@ApiModelProperty(value = "银行卡开户城市,厦门市，上海市", required = true)
    @NotNull(message = "银行卡开户城市不能为空")
	private String banklocityname;

	@ApiModelProperty(value = "银行预留手机号", required = true)
    @NotNull(message = "银行预留手机号不能为空")
	private String phoneno;
	
	@ApiModelProperty(value = "IP", required = true)
    private String orderIp;
	
	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public Double getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(Double transAmt) {
		this.transAmt = transAmt;
	}


	public String getOrderIp() {
		return orderIp;
	}

	public void setOrderIp(String orderIp) {
		this.orderIp = orderIp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
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
