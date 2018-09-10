package com.lottery.orm.dao;

import com.lottery.orm.bo.LotteryPay;

public interface LotteryPayMapper {
    int deleteByPrimaryKey(Integer payid);

    int insert(LotteryPay record);

    int insertSelective(LotteryPay record);

    LotteryPay selectByPrimaryKey(Integer payid);

    int updateByPrimaryKeySelective(LotteryPay record);

    int updateByPrimaryKey(LotteryPay record);
}