package com.lottery.orm.dao;

import com.lottery.orm.bo.TSTimeTask;

public interface TSTimeTaskMapper {

	int deleteByPrimaryKey(String id);

    int insert(TSTimeTask record);

    int insertSelective(TSTimeTask record);

    TSTimeTask selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TSTimeTask record);

    int updateByPrimaryKey(TSTimeTask record);
    
    TSTimeTask selectByTaskId(String taskId);
}