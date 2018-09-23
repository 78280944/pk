package com.lottery.orm.bo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class AccountBankKey {
	
	@ApiModelProperty(value = "编号", required = true)
    private Integer abid;

	@ApiModelProperty(value = "用户ID", required = true)
    private Integer accountid;

    public Integer getAbid() {
        return abid;
    }

    public void setAbid(Integer abid) {
        this.abid = abid;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }
}