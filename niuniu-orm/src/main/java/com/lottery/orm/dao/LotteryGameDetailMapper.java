package com.lottery.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.LotteryGameDetail;
import com.lottery.orm.dto.LotteryGameDetailDto;

public interface LotteryGameDetailMapper {
    int deleteByPrimaryKey(Integer lgdid);

    int insert(LotteryGameDetail record);

    int insertSelective(LotteryGameDetail record);

    LotteryGameDetail selectByPrimaryKey(Integer lgdid);

    int updateByPrimaryKeySelective(LotteryGameDetail record);

    int updateByPrimaryKey(LotteryGameDetail record);
    
    List<LotteryGameDetailDto> selectByGameDetail(Integer lgmid);
    
    int deletePlayer(@Param("accountid")Integer accountid);
}