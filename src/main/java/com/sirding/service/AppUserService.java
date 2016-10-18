package com.sirding.service;

import java.util.List;

import com.sirding.mybatis.model.AppUser;

public interface AppUserService {

	int addAppUser(AppUser obj);
	
	int delAppUser(Integer id);
	
	int updateAppUser(AppUser obj);
	
	AppUser findById(Integer id);
	
	List<AppUser> find(AppUser obj);
}
