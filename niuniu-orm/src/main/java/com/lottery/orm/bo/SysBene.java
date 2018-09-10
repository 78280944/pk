package com.lottery.orm.bo;

import java.math.BigDecimal;

public class SysBene {
    private Integer sbid;

    private String sbtype;

    private BigDecimal amount;

    private BigDecimal benefit;

    private String state;

    public Integer getSbid() {
        return sbid;
    }

    public void setSbid(Integer sbid) {
        this.sbid = sbid;
    }

    public String getSbtype() {
        return sbtype;
    }

    public void setSbtype(String sbtype) {
        this.sbtype = sbtype == null ? null : sbtype.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBenefit() {
        return benefit;
    }

    public void setBenefit(BigDecimal benefit) {
        this.benefit = benefit;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}