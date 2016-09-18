package com.sirding.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sirding.model.User;
import com.sirding.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	private final static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public int addUser(User user) {
		logger.debug("用户名称:" + user.getName());
		return 0;
	}

	@Override
	public int updateUser(User user) {
		logger.debug("更新用户信息...");
		return 0;
	}

	@Override
	public void delUser(User user) {

	}

	@Override
	public User findUser(int id) {
		logger.debug("查找用户ID:" + id);
		return new User();
	}

}
