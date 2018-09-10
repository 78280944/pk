package com.lottery.api.dto;

import java.util.Date;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class BaseAccountInfoVo {

	    @ApiModelProperty(value = "用户名", required = true)
	    private String username;
	    
	    @ApiModelProperty(value = "昵称", required = true)
	    private String ausername;
	    
	    @ApiModelProperty(value = "密码", required = true)
	    private String password;
	    
	    @ApiModelProperty(value = "ip", required = true)
	    private String ip;
	    
	   // @ApiModelProperty(value = "管理账户", required = true)
	  //  private Integer supuserid;
	    
	  //  @ApiModelProperty(value = "管理账户级别,0：超级管理员,1: 一级代理,2：二级代理,3：三级代理", required = true)
	  //  private String level;
	  
	   // @ApiModelProperty(value = "账户类型", required = true)
	   // private String offtype;
	    
		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}


		

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
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

	   
	    
}
