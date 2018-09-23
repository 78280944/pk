package com.lottery.orm.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.lottery.orm.bo.AccountRecord;

public interface AccountRecordMapper {
	
    int deleteByPrimaryKey(String recordid);

	int insert(AccountRecord record);

	int insertSelective(AccountRecord record);

	AccountRecord selectByPrimaryKey(String recordid);

	int updateByPrimaryKeySelective(AccountRecord record);

	int updateByPrimaryKey(AccountRecord record);
	
	AccountRecord selectUserAmount(@Param("startTime")Date startTime,@Param("endTime")Date endTime);

}