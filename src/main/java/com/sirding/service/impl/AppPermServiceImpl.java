package com.sirding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.base.BaseServiceImpl;
import com.sirding.mybatis.mapper.AppPermMapper;
import com.sirding.mybatis.model.AppPerm;
import com.sirding.mybatis.model.AppPermExample;
import com.sirding.service.AppPermService;

@Service
public class AppPermServiceImpl extends BaseServiceImpl implements AppPermService {

	@Autowired
	private AppPermMapper appPermMapper;
	
	@Override
	public int add(AppPerm record) {
		return this.appPermMapper.insert(record);
	}

	@Override
	public int del(Integer id) {
		return this.appPermMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(AppPerm record) {
		return this.appPermMapper.updateByPrimaryKey(record);
	}

	@Override
	public AppPerm findById(Integer id) {
		return this.appPermMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AppPerm> findList(AppPerm record) {
		AppPermExample example = new AppPermExample();
		AppPermExample.Criteria criteria = example.createCriteria();
		if(record != null){
			criteria.andIdEqualTo(record.getId());
		}
		return this.appPermMapper.selectByExample(example);
	}

}
