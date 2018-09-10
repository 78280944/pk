package com.lottery.orm.result;

import com.lottery.orm.dto.UserRechargeResDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserRechargeResult  extends BaseRestResult {
	
	@ApiModelProperty(value = "用于充值记录", required = true)
	private UserRechargeResDto data;

	public void success(UserRechargeResDto data) {
		success();
		this.data = data;
	}

	public UserRechargeResDto getData() {
		return data;
	}

	public void setData(UserRechargeResDto data) {
		this.data = data;
	}
}
