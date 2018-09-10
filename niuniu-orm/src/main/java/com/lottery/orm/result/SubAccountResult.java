package com.lottery.orm.result;

import com.lottery.orm.dto.SubAccountDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class SubAccountResult extends BaseRestResult {
    
    @ApiModelProperty(value = "子账号信息", required = true)
    private SubAccountDto data;

    public void success(SubAccountDto data) {
	success();
	this.data = data;
    }

    public SubAccountDto getData() {
	return data;
    }

    public void setData(SubAccountDto data) {
	this.data = data;
    }

}
