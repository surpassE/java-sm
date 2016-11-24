package com.sirding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.base.BaseServiceImpl;
import com.sirding.mybatis.mapper.AppMenuMapper;
import com.sirding.mybatis.model.AppMenu;
import com.sirding.mybatis.model.AppMenuExample;
import com.sirding.service.AppMenuService;

@Service
public class AppMenuServiceImpl extends BaseServiceImpl<AppMenu> implements AppMenuService {

	@Autowired
	private AppMenuMapper appMenuMapper;
	
	@Override
	public int add(AppMenu record) {
		return this.appMenuMapper.insert(record);
	}

	@Override
	public int del(Integer id) {
		return this.appMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(AppMenu record) {
		return this.appMenuMapper.updateByPrimaryKey(record);
	}

	@Override
	public AppMenu findById(Integer id) {
		return this.appMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AppMenu> findList(AppMenu record) {
		AppMenuExample example = new AppMenuExample();
		AppMenuExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(record.getId());
		return this.appMenuMapper.selectByExample(example);
	}
}
