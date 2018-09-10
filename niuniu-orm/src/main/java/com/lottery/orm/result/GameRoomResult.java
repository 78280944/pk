package com.lottery.orm.result;

import java.util.List;

import com.lottery.orm.bo.LotteryRoom;
import com.lottery.orm.dto.LotteryGameDto;
import com.lottery.orm.dto.LotteryRoomPlayerDto;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class GameRoomResult extends RestResult{
	
	@ApiModelProperty(value = "游戏类型", required = true)
	public List<LotteryRoomPlayerDto> data = null;
	
	public void success(List<LotteryRoomPlayerDto> data) {
		success();
		this.data = data;
	}
	public List<LotteryRoomPlayerDto> getData() {
		return data;
	}

	public void setData(List<LotteryRoomPlayerDto> data) {
		this.data = data;
	}
}
