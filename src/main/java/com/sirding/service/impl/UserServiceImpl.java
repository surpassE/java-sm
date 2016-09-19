package com.sirding.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.mybatis.mapper.UserInfoMapper;
import com.sirding.mybatis.model.UserInfo;
import com.sirding.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public int addUser(UserInfo user) {
		logger.debug("用户名称:" + user.getName());
		int i = this.userInfoMapper.insert(user);
		return i;
	}

	@Override
	public int updateUser(UserInfo user) {
		logger.debug("更新用户信息...");
		return 0;
	}

	@Override
	public void delUser(UserInfo user) {

	}

	@Override
	public UserInfo findUser(int id) {
		logger.debug("查找用户ID:" + id);
		return new UserInfo();
	}

}
