package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class RemarkInfoVo {
	
    @ApiModelProperty(value = "参数，根据不同的参数显示不同内容，默认1")
    private String para;

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}
    
    
}
