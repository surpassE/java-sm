package com.sirding.service;

import com.sirding.model.User;

public interface UserService {

	int addUser(User user);
	
	int updateUser(User user);
	
	void delUser(User user);
	
	User findUser(int id);
}
