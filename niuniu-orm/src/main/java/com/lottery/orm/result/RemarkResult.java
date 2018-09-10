package com.lottery.orm.result;

import com.lottery.orm.dto.AccountInfoDto;
import com.lottery.orm.dto.RemarkDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class RemarkResult extends BaseRestResult{
	
	@ApiModelProperty(value = "会员账号信息", required = true)
	private RemarkDto data;

	public void success(RemarkDto data) {
		success();
		this.data = data;
	}

	public RemarkDto getData() {
		return data;
	}

	public void setData(RemarkDto data) {
		this.data = data;
	}

}
