package com.lottery.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class PasswordInfoVo {
	
	@ApiModelProperty(value = "用户名", required = true)
	@NotNull(message = "用户名不能为空")
    private String username;
	
	@ApiModelProperty(value = "安全密码", required = true)
	@NotNull(message = "安全密码不能为空")
    private String sfcode;
	
	@ApiModelProperty(value = "新密码", required = true)
	@NotNull(message = "新密码不能为空")
    private String password;
	
	@ApiModelProperty(value = "确认密码", required = true)
	@NotNull(message = "确认密码不能为空")
    private String spassword;

	@ApiModelProperty(value = "IP", required = true)
    private String ip;
	
	
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

	public String getSfcode() {
		return sfcode;
	}

	public void setSfcode(String sfcode) {
		this.sfcode = sfcode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpassword() {
		return spassword;
	}

	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}
	
	
}
