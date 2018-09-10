package com.lottery.orm.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserRechargeDto {
	
	@ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    @Min(value=0, message = "用户ID格式不正确")
    private Integer accountId;
	
	@ApiModelProperty(value = "订单号", required = true)
    @NotNull(message = "订单号不能为空")
    private String orderNo;
	
	@ApiModelProperty(value = "订单日期，格式yyyyMMdd", required = true)
    @NotNull(message = "订单日期不能为空")
    private String orderDate;
	
	@ApiModelProperty(value = "开户用户名", required = true)
    @NotNull(message = "开户用户名不能为空")
    private String bankId;

	@ApiModelProperty(value = "银行名称", required = true)
    @NotNull(message = "银行名称不能为空")
    private String bankName;

	@ApiModelProperty(value = "开户行/地址", required = true)
    @NotNull(message = "开户行/地址不能为空")
    private String bankAccount;

	@ApiModelProperty(value = "银行账户", required = true)
    @NotNull(message = "银行账户不能为空")
    private String bankAddress;

	@ApiModelProperty(value = "提现金额", required = true)
    @NotNull(message = "提现金额不能为空")
    private Double transAmt;

	@ApiModelProperty(value = "IP", required = true)
    private String orderIp;
	
	@ApiModelProperty(value = "审批结果状态，03,待处理，04，通过；05，未通过", required = true)
    private String upstate;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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

	public String getUpstate() {
		return upstate;
	}

	public void setUpstate(String upstate) {
		this.upstate = upstate;
	}
	
	
}
