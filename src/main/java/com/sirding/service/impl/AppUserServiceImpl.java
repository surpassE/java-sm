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
	public int addAppUser(AppUser obj) {
		return appUserMapper.insert(obj);
	}

	@Override
	public int delAppUser(Integer id) {
		return appUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateAppUser(AppUser obj) {
		return this.appUserMapper.updateByPrimaryKey(obj);
	}

	@Override
	public AppUser findById(Integer id) {
		return this.appUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AppUser> find(AppUser obj) {
		AppUserExample example = new AppUserExample();
		AppUserExample.Criteria criteria = example.createCriteria();
		criteria.andLoginNameEqualTo(obj.getLoginName());
		return this.appUserMapper.selectByExample(example);
	}

}
