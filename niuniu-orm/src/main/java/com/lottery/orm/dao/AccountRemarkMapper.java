package com.lottery.orm.dao;

import com.lottery.orm.bo.AccountRemark;

public interface AccountRemarkMapper {
    int deleteByPrimaryKey(Integer arid);

    int insert(AccountRemark record);

    int insertSelective(AccountRemark record);

    AccountRemark selectByPrimaryKey(Integer arid);

    int updateByPrimaryKeySelective(AccountRemark record);

    int updateByPrimaryKey(AccountRemark record);
}