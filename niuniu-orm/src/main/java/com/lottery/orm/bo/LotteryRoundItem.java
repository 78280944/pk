package com.lottery.orm.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryRoundItem extends LotteryRoundItemKey {

	@ApiModelProperty(value = "游戏界面显示的赔�?", required = true)
    private Double itemscale;
    
    @ApiModelProperty(value = "赔率更新时间", required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updatetime;

    public Double getItemscale() {
        return itemscale;
    }

    public void setItemscale(Double itemscale) {
        this.itemscale = itemscale;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    
    public boolean equals(Object o) {
        if (o instanceof LotteryRoundItem) {
            LotteryRoundItem i = (LotteryRoundItem) o;
            if (getItemno() == null) {
                return i.getItemno() == null;
            } else {
                return getItemno().equals(i.getItemno());
            }
        } else {
            return false;
        }
    }
}