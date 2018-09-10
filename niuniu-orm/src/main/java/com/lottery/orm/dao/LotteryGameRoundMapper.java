package com.lottery.orm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.LotteryGameRound;
import com.lottery.orm.dto.ResultDataDto;

public interface LotteryGameRoundMapper {
    int deleteByPrimaryKey(Integer lgrid);

	int insert(LotteryGameRound record);

	int insertSelective(LotteryGameRound record);

	LotteryGameRound selectByPrimaryKey(Integer lgrid);

	int updateByPrimaryKeySelective(LotteryGameRound record);

	int updateByPrimaryKey(LotteryGameRound record);
    
    List<ResultDataDto> selectGameResultBytime(@Param("sid")Integer sid,@Param("time")String time,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
   
    List<ResultDataDto> selectGameResult(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("sid")Integer sid,@Param("beginRow")Integer beginRow,@Param("pageSize")Integer pageSize);
   
    LotteryGameRound selectLotteryGameResult(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm);

    List<LotteryGameRound> selectLotteryOrderResult(@Param("sid")Integer sid);
    
    List<LotteryGameRound> selectLotteryOrderPlayer(@Param("startTime")Date startTime,@Param("endTime")Date endTime);
    
    List<LotteryGameRound> selectLotteryPlayoridle();
}