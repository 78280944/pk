package com.lottery.orm.bo;

public class ApiUser {
    private String scratchUser;

    private String scratchToken;

    private String scratchNames;

    public String getScratchUser() {
        return scratchUser;
    }

    public void setScratchUser(String scratchUser) {
        this.scratchUser = scratchUser == null ? null : scratchUser.trim();
    }

    public String getScratchToken() {
        return scratchToken;
    }

    public void setScratchToken(String scratchToken) {
        this.scratchToken = scratchToken == null ? null : scratchToken.trim();
    }

    public String getScratchNames() {
        return scratchNames;
    }

    public void setScratchNames(String scratchNames) {
        this.scratchNames = scratchNames == null ? null : scratchNames.trim();
    }
}