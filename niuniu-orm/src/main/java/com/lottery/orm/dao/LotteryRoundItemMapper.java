package com.lottery.orm.dao;

import com.lottery.orm.bo.LotteryRoundItem;
import com.lottery.orm.bo.LotteryRoundItemKey;

public interface LotteryRoundItemMapper {

	int deleteByPrimaryKey(LotteryRoundItemKey key);

    int insert(LotteryRoundItem record);

    int insertSelective(LotteryRoundItem record);

    LotteryRoundItem selectByPrimaryKey(LotteryRoundItemKey key);

    int updateByPrimaryKeySelective(LotteryRoundItem record);

    int updateByPrimaryKey(LotteryRoundItem record);
}