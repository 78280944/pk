package com.lottery.orm.bo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class LotteryRoom {
	
	@ApiModelProperty(value = "房间编号", required = true)
    private Integer rmid;
	
	@ApiModelProperty(value = "游戏编号", required = true)
    private Integer sid;

	@ApiModelProperty(value = "房间序号", required = true)
    private Integer roomid;

	@ApiModelProperty(value = "房间数量", required = true)
    private Integer roomnumber;

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

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getRoomnumber() {
        return roomnumber;
    }

    public void setRoomnumber(Integer roomnumber) {
        this.roomnumber = roomnumber;
    }
}