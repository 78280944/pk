package com.lottery.orm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.LotteryGame;
import com.lottery.orm.dto.LotteryGameDto;

public interface LotteryGameMapper {
    int insert(LotteryGame record);

    int insertSelective(LotteryGame record);
    
    List<LotteryGameDto> selectLotteryGame(); 
    
    List<LotteryGame> selectLotteryGameItem(@Param("gametype")String gametype);
    
    int updateLotteryTime(@Param("sid")int sid,@Param("gameterm")String gameterm,@Param("gamestarttime")Date gamestarttime,@Param("gameovertime")Date gameovertime);
    
    LotteryGame selectLotteryBySid(@Param("sid")int sid);
}