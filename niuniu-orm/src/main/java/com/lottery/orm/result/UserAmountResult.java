package com.lottery.orm.result;

import com.lottery.orm.dto.UserAmountDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserAmountResult extends BaseRestResult {
	
	@ApiModelProperty(value = "在线人数", required = true)
	private UserAmountDto data;

	public void success(UserAmountDto data) {
		success();
		this.data = data;
	}

	public UserAmountDto getData() {
		return data;
	}

	public void setData(UserAmountDto data) {
		this.data = data;
	}

}
