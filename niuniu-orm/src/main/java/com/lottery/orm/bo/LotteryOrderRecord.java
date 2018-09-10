package com.lottery.orm.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LotteryOrderRecord {
    private Integer lorid;

    private String lotteryterm;

    private Integer sid;

    private String firstvalue;

    private String secondvalue;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date firsttime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date fovertime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date secondtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sovertime;

    private String accoundids;

    public Integer getLorid() {
        return lorid;
    }

    public void setLorid(Integer lorid) {
        this.lorid = lorid;
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

    public String getFirstvalue() {
        return firstvalue;
    }

    public void setFirstvalue(String firstvalue) {
        this.firstvalue = firstvalue == null ? null : firstvalue.trim();
    }

    public String getSecondvalue() {
        return secondvalue;
    }

    public void setSecondvalue(String secondvalue) {
        this.secondvalue = secondvalue == null ? null : secondvalue.trim();
    }

    public Date getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Date firsttime) {
        this.firsttime = firsttime;
    }

    public Date getFovertime() {
        return fovertime;
    }

    public void setFovertime(Date fovertime) {
        this.fovertime = fovertime;
    }

    public Date getSecondtime() {
        return secondtime;
    }

    public void setSecondtime(Date secondtime) {
        this.secondtime = secondtime;
    }

    public Date getSovertime() {
        return sovertime;
    }

    public void setSovertime(Date sovertime) {
        this.sovertime = sovertime;
    }

    public String getAccoundids() {
        return accoundids;
    }

    public void setAccoundids(String accoundids) {
        this.accoundids = accoundids == null ? null : accoundids.trim();
    }
}