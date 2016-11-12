package com.sirding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.mybatis.mapper.AppUserMapper;
import com.sirding.mybatis.model.AppUser;
import com.sirding.mybatis.model.AppUserExample;
import com.sirding.service.AppUserService;

/**
 * 应用用户信息管理
 * @author zc.ding
 * @date 2016年10月18日
 *
 */
@Service
public class AppUserServiceImpl implements AppUserService {
	
	@Autowired
	private AppUserMapper appUserMapper;

	@Override
	public int add(AppUser record) {
		return appUserMapper.insert(record);
	}

	@Override
	public int del(Integer id) {
		return appUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(AppUser record) {
		return this.appUserMapper.updateByPrimaryKey(record);
	}

	@Override
	public AppUser findById(Integer id) {
		return this.appUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AppUser> findList(AppUser record) {
		AppUserExample example = new AppUserExample();
		AppUserExample.Criteria criteria = example.createCriteria();
		if(record != null){
			criteria.andLoginNameEqualTo(record.getLoginName());
		}
		return this.appUserMapper.selectByExample(example);
	}

}
