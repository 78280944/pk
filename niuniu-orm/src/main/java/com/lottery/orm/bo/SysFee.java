package com.lottery.orm.bo;

import java.math.BigDecimal;

public class SysFee {
    private Integer sfid;

    private BigDecimal ratio;

    private Integer time;

    private BigDecimal refee;

    private BigDecimal cafee;

    public Integer getSfid() {
        return sfid;
    }

    public void setSfid(Integer sfid) {
        this.sfid = sfid;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public BigDecimal getRefee() {
        return refee;
    }

    public void setRefee(BigDecimal refee) {
        this.refee = refee;
    }

    public BigDecimal getCafee() {
        return cafee;
    }

    public void setCafee(BigDecimal cafee) {
        this.cafee = cafee;
    }
}