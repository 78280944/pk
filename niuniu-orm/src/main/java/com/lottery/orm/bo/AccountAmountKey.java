package com.lottery.orm.bo;

public class AccountAmountKey {
    private Integer aaid;

    private Integer accountid;

    private String lotteryterm;

    private Integer sid;

    public Integer getAaid() {
        return aaid;
    }

    public void setAaid(Integer aaid) {
        this.aaid = aaid;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getLotteryterm() {
        return lotteryterm;
    }

    public void setLotteryterm(String lotteryterm) {
        this.lotteryterm = lotteryterm == null ? null : lotteryterm.trim();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}