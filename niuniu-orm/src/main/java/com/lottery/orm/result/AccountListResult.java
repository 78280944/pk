package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.dto.AccountInfoDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountListResult extends BaseRestResult {
	@ApiModelProperty(value = "玩家账户列表数据", required = true)
	private List<AccountInfoDto> data = null;

	public void success(List<AccountInfoDto> data) {
		success();
		this.data = data;
	}

	public List<AccountInfoDto> getData() {
		return data;
	}

	public void setData(List<AccountInfoDto> list) {
		this.data = list;
	}

}
