package com.lottery.orm.dao;

import com.lottery.orm.bo.LotteryOrderResult;

public interface LotteryOrderResultMapper {

	int deleteByPrimaryKey(Integer resultid);

    int insert(LotteryOrderResult record);

    int insertSelective(LotteryOrderResult record);

    LotteryOrderResult selectByPrimaryKey(Integer resultid);

    int updateByPrimaryKeySelective(LotteryOrderResult record);

    int updateByPrimaryKey(LotteryOrderResult record);
}