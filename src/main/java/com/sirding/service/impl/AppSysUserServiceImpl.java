package com.sirding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.base.BaseServiceImpl;
import com.sirding.mybatis.mapper.AppSysUserMapper;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppSysUserExample;
import com.sirding.service.AppSysUserService;

@Service
public class AppSysUserServiceImpl extends BaseServiceImpl implements AppSysUserService {

	@Autowired
	private AppSysUserMapper appSysUserMapper;
	@Override
	public int add(AppSysUser record) {
		return this.appSysUserMapper.insert(record);
	}

	@Override
	public int del(Integer id) {
		return this.appSysUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(AppSysUser record) {
		return this.appSysUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public AppSysUser findById(Integer id) {
		return this.appSysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AppSysUser> findList(AppSysUser record) {
		AppSysUserExample example = new AppSysUserExample();
		AppSysUserExample.Criteria criteria = example.createCriteria();
		if(record != null){
//			criteria.andIdEqualTo(record.getId());
			criteria.andLoginNameEqualTo(record.getLoginName());
		}
		return this.appSysUserMapper.selectByExample(example);
	}

}
