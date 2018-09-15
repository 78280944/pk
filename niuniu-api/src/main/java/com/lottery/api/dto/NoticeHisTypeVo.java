package com.lottery.api.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class NoticeHisTypeVo extends PageParamVo {
	
	@ApiModelProperty(value = "账户类型，3：玩家账户", required = true)
	private String offtype;

	public String getOfftype() {
		return offtype;
	}

	public void setOfftype(String offtype) {
		this.offtype = offtype;
	}


}
