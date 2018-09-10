package com.lottery.orm.dao;

import com.lottery.orm.bo.LotteryService;

public interface LotteryServiceMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(LotteryService record);

    int insertSelective(LotteryService record);

    LotteryService selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(LotteryService record);

    int updateByPrimaryKey(LotteryService record);
}