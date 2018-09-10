package com.lottery.orm.dao;

import com.lottery.orm.bo.LotteryItem;

public interface LotteryItemMapper {

	int deleteByPrimaryKey(Integer itemid);

	int insert(LotteryItem record);

	int insertSelective(LotteryItem record);

	LotteryItem selectByPrimaryKey(Integer itemid);

	int updateByPrimaryKeySelective(LotteryItem record);

	int updateByPrimaryKey(LotteryItem record);

}