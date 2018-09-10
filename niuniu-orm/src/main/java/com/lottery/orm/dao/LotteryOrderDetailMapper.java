package com.lottery.orm.dao;

import com.lottery.orm.bo.LotteryOrderDetail;
import com.lottery.orm.bo.LotteryOrderDetailKey;

public interface LotteryOrderDetailMapper {

	int deleteByPrimaryKey(LotteryOrderDetailKey key);

    int insert(LotteryOrderDetail record);

    int insertSelective(LotteryOrderDetail record);

    LotteryOrderDetail selectByPrimaryKey(LotteryOrderDetailKey key);

    int updateByPrimaryKeySelective(LotteryOrderDetail record);

    int updateByPrimaryKey(LotteryOrderDetail record);
}