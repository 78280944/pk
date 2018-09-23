package com.lottery.orm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.AccountBank;
import com.lottery.orm.bo.AccountBankKey;
import com.lottery.orm.dto.AccBankDto;
import com.lottery.orm.dto.ResultDataDto;

public interface AccountBankMapper {
    int deleteByPrimaryKey(AccountBankKey key);

    int insert(AccountBank record);

    int insertSelective(AccountBank record);

    AccountBank selectByPrimaryKey(AccountBankKey key);

    int updateByPrimaryKeySelective(AccountBank record);

    int updateByPrimaryKey(AccountBank record);
    
    List<AccBankDto> selectByAccount(@Param("accountid")Integer accountid);
   
}