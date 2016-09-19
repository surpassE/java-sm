package com.sirding.service;

import com.sirding.mybatis.model.UserInfo;

public interface UserService {

	int addUser(UserInfo user);
	
	int updateUser(UserInfo user);
	
	void delUser(UserInfo user);
	
	UserInfo findUser(int id);
}
