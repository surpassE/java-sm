package com.sirding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sirding.mybatis.mapper.OauthClientDetailsMapper;
import com.sirding.mybatis.model.OauthClientDetails;
import com.sirding.mybatis.model.OauthClientDetailsExample;
import com.sirding.service.OauthClientDetailsService;


/**
 * @Described	: oauth_client_detail接口实现类
 * @project		: com.sirding.service.impl.OauthClientDetailsServiceImpl
 * @author 		: zc.ding
 * @date 		: 2016年11月15日
 */
@Service("oauthClientDetailsService")
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {

	@Autowired
	private OauthClientDetailsMapper oauthClientDetailsMapper;
	
	@Override
	public int add(OauthClientDetails record) {
		return this.oauthClientDetailsMapper.insert(record);
	}

	@Override
	public int del(Integer id) {
		return 0;
	}

	@Override
	public int update(OauthClientDetails record) {
		OauthClientDetailsExample example = new OauthClientDetailsExample();
		if(record != null){
			example.createCriteria().andClientIdEqualTo(record.getClientId());
		}
		return this.oauthClientDetailsMapper.updateByExample(record, example);
	}

	@Override
	public OauthClientDetails findById(Integer id) {
		return null;
	}

	@Override
	public List<OauthClientDetails> findList(OauthClientDetails record) {
		OauthClientDetailsExample example = new OauthClientDetailsExample();
		OauthClientDetailsExample.Criteria criteria = example.createCriteria();
		if(record != null){
			criteria.andClientIdEqualTo(record.getClientId());
		}
		return this.oauthClientDetailsMapper.selectByExample(example);
	}

	@Override
	public int del(String clientId) {
		return this.oauthClientDetailsMapper.deleteByPrimaryKey(clientId);
	}

	@Override
	public OauthClientDetails findByClientId(String clientId) {
		return this.oauthClientDetailsMapper.selectByPrimaryKey(clientId);
	}

}
