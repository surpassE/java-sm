package com.sirding.service;

import java.util.List;

import com.sirding.mybatis.model.UserInfo;

public interface UserService {

	int addUser(UserInfo user);
	
	int addUserNest(UserInfo user);
	
	int updateUser(UserInfo user);
	
	int delUser(UserInfo user);
	
	UserInfo findUser(int id);
	
	List<UserInfo> findUsers(UserInfo user);
}
