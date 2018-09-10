package com.lottery.orm.dao;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.LotteryOrder;

public interface LotteryOrderMapper {
	int deleteByPrimaryKey(Integer orderid);

	int insert(LotteryOrder record);

	int insertSelective(LotteryOrder record);

	LotteryOrder selectByPrimaryKey(Integer orderid);

	int updateByPrimaryKeySelective(LotteryOrder record);

	int updateByPrimaryKey(LotteryOrder record);


}