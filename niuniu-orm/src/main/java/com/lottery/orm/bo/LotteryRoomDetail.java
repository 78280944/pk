package com.lottery.orm.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryRoomDetail {
    private Integer ltdid;

    private Integer rmid;

    private Integer sid;

    private String lotteryterm;
    
	@ApiModelProperty(value = "游戏开始时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gamestarttime;
	
	@ApiModelProperty(value = "游戏结束时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gameovertime;

    private String no1;

    private String no2;

    private String no3;

    private String no4;

    private String no5;

    private String no6;

    private String no7;

    private String no8;

    private String no9;

    private String no10;

    public Integer getLtdid() {
        return ltdid;
    }

    public void setLtdid(Integer ltdid) {
        this.ltdid = ltdid;
    }

    public Integer getRmid() {
        return rmid;
    }

    public void setRmid(Integer rmid) {
        this.rmid = rmid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getLotteryterm() {
        return lotteryterm;
    }

    public void setLotteryterm(String lotteryterm) {
        this.lotteryterm = lotteryterm == null ? null : lotteryterm.trim();
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

    public String getNo1() {
        return no1;
    }

    public void setNo1(String no1) {
        this.no1 = no1 == null ? null : no1.trim();
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2 == null ? null : no2.trim();
    }

    public String getNo3() {
        return no3;
    }

    public void setNo3(String no3) {
        this.no3 = no3 == null ? null : no3.trim();
    }

    public String getNo4() {
        return no4;
    }

    public void setNo4(String no4) {
        this.no4 = no4 == null ? null : no4.trim();
    }

    public String getNo5() {
        return no5;
    }

    public void setNo5(String no5) {
        this.no5 = no5 == null ? null : no5.trim();
    }

    public String getNo6() {
        return no6;
    }

    public void setNo6(String no6) {
        this.no6 = no6 == null ? null : no6.trim();
    }

    public String getNo7() {
        return no7;
    }

    public void setNo7(String no7) {
        this.no7 = no7 == null ? null : no7.trim();
    }

    public String getNo8() {
        return no8;
    }

    public void setNo8(String no8) {
        this.no8 = no8 == null ? null : no8.trim();
    }

    public String getNo9() {
        return no9;
    }

    public void setNo9(String no9) {
        this.no9 = no9 == null ? null : no9.trim();
    }

    public String getNo10() {
        return no10;
    }

    public void setNo10(String no10) {
        this.no10 = no10 == null ? null : no10.trim();
    }

}