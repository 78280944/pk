package com.lottery.orm.bo;

public class LotteryService {
    private Integer sid;

    private String loginservice;

    private String registersercice;

    private String addedservice;

    private String playservice;

    private String aremarksercie;

    private String dremarksercie;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getLoginservice() {
        return loginservice;
    }

    public void setLoginservice(String loginservice) {
        this.loginservice = loginservice == null ? null : loginservice.trim();
    }

    public String getRegistersercice() {
        return registersercice;
    }

    public void setRegistersercice(String registersercice) {
        this.registersercice = registersercice == null ? null : registersercice.trim();
    }

    public String getAddedservice() {
        return addedservice;
    }

    public void setAddedservice(String addedservice) {
        this.addedservice = addedservice == null ? null : addedservice.trim();
    }

    public String getPlayservice() {
        return playservice;
    }

    public void setPlayservice(String playservice) {
        this.playservice = playservice == null ? null : playservice.trim();
    }

    public String getAremarksercie() {
        return aremarksercie;
    }

    public void setAremarksercie(String aremarksercie) {
        this.aremarksercie = aremarksercie == null ? null : aremarksercie.trim();
    }

    public String getDremarksercie() {
        return dremarksercie;
    }

    public void setDremarksercie(String dremarksercie) {
        this.dremarksercie = dremarksercie == null ? null : dremarksercie.trim();
    }
}