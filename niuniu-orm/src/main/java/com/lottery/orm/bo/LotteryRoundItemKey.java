package com.lottery.orm.bo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryRoundItemKey {

	@ApiModelProperty(value = "游戏ID", required = true)
    private Integer roundid;
    
    @ApiModelProperty(value = "投注项编�?", required = true)
    private String itemno;

    public Integer getRoundid() {
        return roundid;
    }

    public void setRoundid(Integer roundid) {
        this.roundid = roundid;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno == null ? null : itemno.trim();
    }
}