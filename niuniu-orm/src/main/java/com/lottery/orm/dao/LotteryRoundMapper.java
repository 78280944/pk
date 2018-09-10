package com.lottery.orm.dao;

import com.lottery.orm.bo.LotteryRound;

public interface LotteryRoundMapper {

	int deleteByPrimaryKey(Integer roundid);

    int insert(LotteryRound record);

    int insertSelective(LotteryRound record);

    LotteryRound selectByPrimaryKey(Integer roundid);

    int updateByPrimaryKeySelective(LotteryRound record);

    int updateByPrimaryKey(LotteryRound record);
}