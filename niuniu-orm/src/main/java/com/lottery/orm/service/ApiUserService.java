package com.lottery.orm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lottery.orm.bo.ApiUser;
import com.lottery.orm.dao.ApiUserMapper;

@Service
@Transactional
public class ApiUserService {

	@Autowired
	private ApiUserMapper apiUserMapper;

	// 添加API用户
	public int addApiUser(ApiUser user) {
		try {
			return apiUserMapper.insertSelective(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 获取API用户
	public ApiUser getApiUser(String scratchUser) {
		try {
			return apiUserMapper.selectByPrimaryKey(scratchUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 更新API用户
	public int updateApiUser(ApiUser user) {
		try {
			return apiUserMapper.updateByPrimaryKey(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
