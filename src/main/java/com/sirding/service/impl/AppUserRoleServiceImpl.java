package com.sirding.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.base.BaseServiceImpl;
import com.sirding.mybatis.mapper.AppUserRoleMapper;
import com.sirding.mybatis.model.AppUserRole;
import com.sirding.service.AppUserRoleService;

@Service
public class AppUserRoleServiceImpl extends BaseServiceImpl<AppUserRole> implements AppUserRoleService {

	private AppUserRoleMapper appUserRoleMapper;

	@Autowired
	public void setAppUserRoleMapper(AppUserRoleMapper appUserRoleMapper) {
		this.appUserRoleMapper = appUserRoleMapper;
		super.mapper = this.appUserRoleMapper;
	} 
	
}
