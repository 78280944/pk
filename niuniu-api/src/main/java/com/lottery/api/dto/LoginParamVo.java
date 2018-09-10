package com.lottery.api.dto;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LoginParamVo {
	
	@ApiModelProperty(value = "用户名", required = true)
	@NotNull(message = "用户名不能为空")
	protected String username;
		
	@ApiModelProperty(value = "用户密码", required = true)
	@NotNull(message = "用户密码不能为空")
    private String password;
	
	@ApiModelProperty(value = "IP", required = true)
	private String ip;
	
	//private String  
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
