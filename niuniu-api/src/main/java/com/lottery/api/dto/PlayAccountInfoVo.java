package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class PlayAccountInfoVo extends BaseAccountInfoVo{
	
	   // @ApiModelProperty(value = "点数限额")
	  //  private Double limited;
	    @ApiModelProperty(value = "电话")
	    private String phone; 
	    
	    @ApiModelProperty(value = "微信")
	    private String webchat;
	    
	    @ApiModelProperty(value = "银行户名")
	    private String bankid;
	    
	    @ApiModelProperty(value = "银行名称")
	    private String bankname;
	    
	    @ApiModelProperty(value = "开户行")
	    private String bankaddress;
	    
	    @ApiModelProperty(value = "银行账号")
	    private String bankaccount;
	    
		@ApiModelProperty(value = "银行编码，例如工商银行，1", required = true)
	    private String bankno;
		
		@ApiModelProperty(value = "银行开户省份", required = true)
	    private String bankloproname;
		
		@ApiModelProperty(value = "银行开户城市", required = true)
	    private String banklocityname;
		
		@ApiModelProperty(value = "银行预留手机号码", required = true)
	    private String phoneno;
	    
	    @ApiModelProperty(value = "邀请码")
	    private String code;
	    
	    @ApiModelProperty(value = "安全码")
	    private String sfcode;
	        
	    
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
