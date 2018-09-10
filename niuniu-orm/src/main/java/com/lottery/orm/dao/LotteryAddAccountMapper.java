package com.lottery.orm.dao;

import java.util.List;

import com.lottery.orm.bo.LotteryAddAccount;

public interface LotteryAddAccountMapper {
    int deleteByPrimaryKey(Integer laaid);

    int insert(LotteryAddAccount record);

    int insertSelective(LotteryAddAccount record);

    LotteryAddAccount selectByPrimaryKey(Integer laaid);
    
    List<LotteryAddAccount> selectByOffAccount();

    int updateByPrimaryKeySelective(LotteryAddAccount record);

    int updateByPrimaryKey(LotteryAddAccount record);
}