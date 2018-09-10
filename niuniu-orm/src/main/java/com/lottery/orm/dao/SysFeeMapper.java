package com.lottery.orm.dao;

import com.lottery.orm.bo.SysFee;

public interface SysFeeMapper {
    int deleteByPrimaryKey(Integer sfid);

    int insert(SysFee record);

    int insertSelective(SysFee record);

    SysFee selectByPrimaryKey(Integer sfid);

    int updateByPrimaryKeySelective(SysFee record);

    int updateByPrimaryKey(SysFee record);
}