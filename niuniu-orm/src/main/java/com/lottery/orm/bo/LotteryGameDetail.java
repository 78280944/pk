package com.lottery.orm.bo;

import java.math.BigDecimal;

public class LotteryGameDetail {
    private Integer lgdid;

    private Integer lgmid;

    private Integer accountid;

    private BigDecimal trademoney;

    private Integer noid;

    private Integer raccountid;

    private Integer rnoid;

    private String rresult;

    public Integer getLgdid() {
        return lgdid;
    }

    public void setLgdid(Integer lgdid) {
        this.lgdid = lgdid;
    }

    public Integer getLgmid() {
        return lgmid;
    }

    public void setLgmid(Integer lgmid) {
        this.lgmid = lgmid;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public BigDecimal getTrademoney() {
        return trademoney;
    }

    public void setTrademoney(BigDecimal trademoney) {
        this.trademoney = trademoney;
    }

    public Integer getNoid() {
        return noid;
    }

    public void setNoid(Integer noid) {
        this.noid = noid;
    }

    public Integer getRaccountid() {
        return raccountid;
    }

    public void setRaccountid(Integer raccountid) {
        this.raccountid = raccountid;
    }

    public Integer getRnoid() {
        return rnoid;
    }

    public void setRnoid(Integer rnoid) {
        this.rnoid = rnoid;
    }

    public String getRresult() {
        return rresult;
    }

    public void setRresult(String rresult) {
        this.rresult = rresult == null ? null : rresult.trim();
    }
}