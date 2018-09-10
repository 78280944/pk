package com.lottery.orm.dao;

import java.util.List;

import com.lottery.orm.bo.BankCash;

public interface BankCashMapper {
    int deleteByPrimaryKey(Integer bcid);

    int insert(BankCash record);

    int insertSelective(BankCash record);

    BankCash selectByPrimaryKey(Integer bcid);

    int updateByPrimaryKeySelective(BankCash record);

    int updateByPrimaryKey(BankCash record);
    
    List<BankCash> selectBankCash();
}