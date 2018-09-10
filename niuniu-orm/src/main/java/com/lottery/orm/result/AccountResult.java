package com.lottery.orm.result;

import com.lottery.orm.dto.AccountInfoDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountResult extends BaseRestResult {

	@ApiModelProperty(value = "会员账号信息", required = true)
	private AccountInfoDto data;

	public void success(AccountInfoDto data) {
		success();
		this.data = data;
	}

	public AccountInfoDto getData() {
		return data;
	}

	public void setData(AccountInfoDto data) {
		this.data = data;
	}

}
