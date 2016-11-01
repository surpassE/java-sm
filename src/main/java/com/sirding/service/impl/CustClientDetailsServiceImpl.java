package com.sirding.service.impl;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.sirding.service.CustClientDetailsService;

/**
 * 管理oauth2.0的grant type，替代xml中oauth2:client-details-service节点
 * @author zc.ding
 * @date 2016年10月31日
 */
@Service("custClientDetailsService")
public class CustClientDetailsServiceImpl implements CustClientDetailsService{
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		
		return null;
	}

}
