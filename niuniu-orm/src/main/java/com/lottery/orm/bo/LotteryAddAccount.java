package com.lottery.orm.bo;

public class LotteryAddAccount {
    private Integer laaid;

    private Integer accountid;

    private String username;

    private String state;

    public Integer getLaaid() {
        return laaid;
    }

    public void setLaaid(Integer laaid) {
        this.laaid = laaid;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}