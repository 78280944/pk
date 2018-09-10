package com.lottery.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.LotteryGame;
import com.lottery.orm.bo.LotteryGameResults;
import com.lottery.orm.bo.LotteryGameResultsKey;
import com.lottery.orm.dto.LotteryResultDto;

public interface LotteryGameResultsMapper {
    int deleteByPrimaryKey(LotteryGameResultsKey key);

	int insert(LotteryGameResults record);

	int insertSelective(LotteryGameResults record);

	LotteryGameResults selectByPrimaryKey(LotteryGameResultsKey key);

	int updateByPrimaryKeySelective(LotteryGameResults record);

	int updateByPrimaryKey(LotteryGameResults record);
    
    int insertBatch(LotteryGame record);
    
    List<LotteryGameResults> selectGameResults(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm);

    List<LotteryGameResults> selectGameOrder(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm);
    
    int updateOrders(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm);
    
    List<LotteryResultDto> selectSidGameResult(@Param("sid")Integer sid,@Param("lotteryterm")String lotteryterm);
    
}