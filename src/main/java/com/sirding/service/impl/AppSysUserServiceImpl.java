package com.sirding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.base.BaseServiceImpl;
import com.sirding.domain.dtpage.Page;
import com.sirding.mybatis.mapper.AppSysUserMapper;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppSysUserExample;
import com.sirding.service.AppSysUserService;

@Service
public class AppSysUserServiceImpl extends BaseServiceImpl<AppSysUser> implements AppSysUserService {

	private AppSysUserMapper appSysUserMapper;
	
	@Autowired
	public void setAppSysUserMapper(AppSysUserMapper appSysUserMapper) {
		this.appSysUserMapper = appSysUserMapper;
		super.mapper = this.appSysUserMapper;
	}

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
		return this.appSysUserMapper.selectByExample(this.initExample(record));
	}
	
//	@Override
//	public long findSysUserCount(AppSysUser record) {
//		AppSysUserExample example = this.initExample(record);
//		return this.appSysUserMapper.countByExample(example);
//	}

	@Override
	public List<AppSysUser> findSysUser(Page page, AppSysUser record) {
		return super.findByPage(page, this.initExample(record));
	}
	
	/**
	 * @Described	: 初始化查询条件
	 * @author		: zc.ding
	 * @date 		: 2016年11月23日
	 * @return		: AppSysUserExample
	 * @param record
	 * @return
	 */
	private AppSysUserExample initExample(AppSysUser record){
		AppSysUserExample example = new AppSysUserExample();
		AppSysUserExample.Criteria criteria = example.createCriteria();
		if(record != null){
			if(record.getLoginName() != null){
				criteria.andLoginNameEqualTo(record.getLoginName());
			}
		}
		return example;
	}

}
