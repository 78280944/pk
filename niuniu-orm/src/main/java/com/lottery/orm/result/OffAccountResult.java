package com.lottery.orm.result;

import com.lottery.orm.dto.OffAccountDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * POJO class for rest process result.
 * 
 */
public class OffAccountResult extends BaseRestResult {

	@ApiModelProperty(value = "代理账号信息", required = true)
	private OffAccountDto data;

	public void success(OffAccountDto data) {
		success();
		this.data = data;
	}

	public OffAccountDto getData() {
		return data;
	}

	public void setData(OffAccountDto data) {
		this.data = data;
	}

}
