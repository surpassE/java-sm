package com.sirding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.base.BaseServiceImpl;
import com.sirding.mybatis.mapper.AppRoleMapper;
import com.sirding.mybatis.model.AppRole;
import com.sirding.mybatis.model.AppRoleExample;
import com.sirding.service.AppRoleService;

@Service
public class AppRoleServiceImpl extends BaseServiceImpl implements AppRoleService {

	@Autowired
	private AppRoleMapper appRoleMapper;
	@Override
	public int add(AppRole record) {
		return this.appRoleMapper.insert(record);
	}

	@Override
	public int del(Integer id) {
		return this.appRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(AppRole record) {
		return this.appRoleMapper.updateByPrimaryKey(record);
	}

	@Override
	public AppRole findById(Integer id) {
		return this.appRoleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AppRole> findList(AppRole record) {
		AppRoleExample example = new AppRoleExample();
		AppRoleExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(record.getId());
		return this.appRoleMapper.selectByExample(example);
	}

	@Override
	public List<AppRole> findRoleByUserName(String userName) {
		return this.appRoleMapper.findRoleByUserName(userName);
	}

	@Override
	public List<AppRole> findRoleBySysUserName(String userName) {
		return this.appRoleMapper.findRoleBySysUserName(userName);
	}

}
