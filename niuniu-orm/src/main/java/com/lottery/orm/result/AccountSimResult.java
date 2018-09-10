package com.lottery.orm.result;

import com.lottery.orm.dto.AccountSimInfoDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountSimResult extends BaseRestResult {
	
	@ApiModelProperty(value = "账号信息", required = true)
	private AccountSimInfoDto data;

	public void success(AccountSimInfoDto data) {
		success();
		this.data = data;
	}

	public AccountSimInfoDto getData() {
		return data;
	}

	public void setData(AccountSimInfoDto data) {
		this.data = data;
	}
}
