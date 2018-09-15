package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class PlayAccountInfoVo{
	
	    @ApiModelProperty(value = "用户名")
	    private String username; 
	    
	    @ApiModelProperty(value = "手机号")
	    private String phone;
	    
	    @ApiModelProperty(value = "代理ID")
	    private String supuserid;
	    
	    @ApiModelProperty(value = "密码")
	    private String password;
	    
	    @ApiModelProperty(value = "IP")
	    private String ip;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getSupuserid() {
			return supuserid;
		}

		public void setSupuserid(String supuserid) {
			this.supuserid = supuserid;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}
	    
	   

}
