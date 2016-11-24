package com.sirding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.base.BaseServiceImpl;
import com.sirding.mybatis.mapper.AppMenuMapper;
import com.sirding.mybatis.mapper.AppPermMapper;
import com.sirding.mybatis.mapper.AppRoleMapper;
import com.sirding.mybatis.mapper.AppRoleMenuMapper;
import com.sirding.mybatis.mapper.AppRolePermMapper;
import com.sirding.mybatis.mapper.AppSysUserMapper;
import com.sirding.mybatis.mapper.AppSysUserRoleMapper;
import com.sirding.mybatis.mapper.AppUserMapper;
import com.sirding.mybatis.mapper.AppUserRoleMapper;
import com.sirding.mybatis.model.AppMenu;
import com.sirding.mybatis.model.AppPerm;
import com.sirding.mybatis.model.AppRole;
import com.sirding.service.SecService;

@Service
public class SecServiceImpl extends BaseServiceImpl<Object> implements SecService {

	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private AppSysUserMapper appSysUserMapper;
	@Autowired
	private AppRoleMapper appRoleMapper;
	@Autowired
	private AppMenuMapper appMenuMapper;
	@Autowired
	private AppPermMapper appPermMapper;
	@Autowired
	private AppRoleMenuMapper appRoleMenuMapper;
	@Autowired
	private AppRolePermMapper appRolePermMapper;
	@Autowired
	private AppUserRoleMapper appUserRoleMapper;
	@Autowired
	private AppSysUserRoleMapper appSysUserRoleMapper;
	@Override
	public List<AppMenu> findMenuListByRoleId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<AppMenu> findMenuListByUserId(Integer id) {
		return this.appMenuMapper.findMenuListByUserId(id);
	}
	
	@Override
	public List<AppMenu> findMenuListBySysUserId(Integer id) {
		return this.appMenuMapper.findMenuListBySysUserId(id);
	}
	
	@Override
	public List<AppPerm> findPermListByRoleId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<AppPerm> findPermListByUserId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<AppRole> findRoleListByUserId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
