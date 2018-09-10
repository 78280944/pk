package com.lottery.orm.dao;

import com.lottery.orm.bo.AccountRecord;

public interface AccountRecordMapper {
	
    int deleteByPrimaryKey(String recordid);

	int insert(AccountRecord record);

	int insertSelective(AccountRecord record);

	AccountRecord selectByPrimaryKey(String recordid);

	int updateByPrimaryKeySelective(AccountRecord record);

	int updateByPrimaryKey(AccountRecord record);

}