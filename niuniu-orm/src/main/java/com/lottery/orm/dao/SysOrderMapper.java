package com.lottery.orm.dao;

import com.lottery.orm.bo.SysOrder;

public interface SysOrderMapper {
    int deleteByPrimaryKey(Integer soid);

    int insert(SysOrder record);

    int insertSelective(SysOrder record);

    SysOrder selectByPrimaryKey(Integer soid);

    int updateByPrimaryKeySelective(SysOrder record);

    int updateByPrimaryKey(SysOrder record);
    
    SysOrder selectByOrderSid(Integer sid);
}