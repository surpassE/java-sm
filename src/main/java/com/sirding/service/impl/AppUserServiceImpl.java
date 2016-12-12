package com.sirding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.base.BaseServiceImpl;
import com.sirding.domain.PageAdapter;
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
public class AppUserServiceImpl extends BaseServiceImpl<AppUser> implements AppUserService {
	
	private AppUserMapper appUserMapper;
	
	@Autowired
	public void setAppUserMapper(AppUserMapper appUserMapper) {
		this.appUserMapper = appUserMapper;
		super.mapper = this.appUserMapper;
	}

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
		AppUserExample example = this.initExample(record);
		return this.appUserMapper.selectByExample(example);
	}

//	@Override
//	public long findUserCount(AppUser record) {
//		return this.appUserMapper.countByExample(this.initExample(record));
//	}

	@Override
	public PageAdapter<AppUser> findUser(PageAdapter<AppUser> page, AppUser record) {
		return super.findByPages(page, this.initExample(record));
	}
	
	/**
	 * @Described	: 初始化检索条件
	 * @author		: zc.ding
	 * @date 		: 2016年11月23日
	 * @return		: AppUserExample
	 * @param record
	 * @return
	 */
	private AppUserExample initExample(AppUser record){
		AppUserExample example = new AppUserExample();
		AppUserExample.Criteria criteria = example.createCriteria();
		if(record != null){
			if(record.getLoginName() != null){
				criteria.andLoginNameEqualTo(record.getLoginName());
			}
		}
		return example;
	}
}
