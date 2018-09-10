package com.lottery.orm.dto;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountInfoDto extends BaseAccountDto {
	
	
	@ApiModelProperty(value = "账户余额", required = true)
	private BigDecimal usermoney;
	
	@ApiModelProperty(value = "电话号码", required = true)
	private String phone;
	
	@ApiModelProperty(value = "微信号", required = true)
	private String webchat;
	
	@ApiModelProperty(value = "代理占成", required = true)
	private Double percentage;
	
	@ApiModelProperty(value = "管理权限", required = true)
	private String query;
	
	@ApiModelProperty(value = "账户类型", required = true)
	private String offtype;
	
	@ApiModelProperty(value = "邀请码", required = true)
	private String code;
	
	@ApiModelProperty(value = "安全认证码", required = true)
	private String sfcode;
	
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
	
	@ApiModelProperty(value = "接口口令", required = true)
	private String token;
	
	@ApiModelProperty(value = "登录记录流水号", required = true)
	private String recordid;


	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public BigDecimal getUsermoney() {
		return usermoney;
	}

	public void setUsermoney(BigDecimal usermoney) {
		this.usermoney = usermoney;
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

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getOfftype() {
		return offtype;
	}

	public void setOfftype(String offtype) {
		this.offtype = offtype;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSfcode() {
		return sfcode;
	}

	public void setSfcode(String sfcode) {
		this.sfcode = sfcode;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
