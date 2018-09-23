package com.lottery.orm.bo;

import java.math.BigDecimal;

public class SysAgency {
    private Integer said;

    private String level;

    private String levalname;

    private Integer amount;

    private BigDecimal agratio;

    private BigDecimal asratio;

    public Integer getSaid() {
        return said;
    }

    public void setSaid(Integer said) {
        this.said = said;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getLevalname() {
        return levalname;
    }

    public void setLevalname(String levalname) {
        this.levalname = levalname == null ? null : levalname.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getAgratio() {
        return agratio;
    }

    public void setAgratio(BigDecimal agratio) {
        this.agratio = agratio;
    }

    public BigDecimal getAsratio() {
        return asratio;
    }

    public void setAsratio(BigDecimal asratio) {
        this.asratio = asratio;
    }
}