package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DemoInfoVo {
	
    @ApiModelProperty(value = "IP", required = true)
    private String ip;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
    
}
