package com.lottery.orm.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryGame {
    private Integer sid;

    private String gametype;

    private String gamename;

    private String gameterm;

    private String gamelobbyno;

    private String gamelobbyname;
    
	@ApiModelProperty(value = "游戏开始时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gamestarttime;
    
	@ApiModelProperty(value = "游戏结束时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gameovertime;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getGametype() {
        return gametype;
    }

    public void setGametype(String gametype) {
        this.gametype = gametype == null ? null : gametype.trim();
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename == null ? null : gamename.trim();
    }

    public String getGameterm() {
        return gameterm;
    }

    public void setGameterm(String gameterm) {
        this.gameterm = gameterm == null ? null : gameterm.trim();
    }

    public String getGamelobbyno() {
        return gamelobbyno;
    }

    public void setGamelobbyno(String gamelobbyno) {
        this.gamelobbyno = gamelobbyno == null ? null : gamelobbyno.trim();
    }

    public String getGamelobbyname() {
        return gamelobbyname;
    }

    public void setGamelobbyname(String gamelobbyname) {
        this.gamelobbyname = gamelobbyname == null ? null : gamelobbyname.trim();
    }

    public Date getGamestarttime() {
        return gamestarttime;
    }

    public void setGamestarttime(Date gamestarttime) {
        this.gamestarttime = gamestarttime;
    }

    public Date getGameovertime() {
        return gameovertime;
    }

    public void setGameovertime(Date gameovertime) {
        this.gameovertime = gameovertime;
    }
}