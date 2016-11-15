package com.sirding.service;

import com.sirding.base.CurdService;
import com.sirding.mybatis.model.OauthClientDetails;

/**
 * @Described	: oauth_client_details操作接口
 * @project		: com.sirding.service.OauthClientDetailsService
 * @author 		: zc.ding
 * @date 		: 2016年11月15日
 */
public interface OauthClientDetailsService extends CurdService<OauthClientDetails>{

	/**
	 * @Described	: 通过client删除指定的oauth client details
	 * @author		: zc.ding
	 * @date 		: 2016年11月16日
	 * @return		: int
	 * @param clientId
	 */
	int del(String clientId);
	
	/**
	 * @Described	: 通过client查询指定的oauth client details信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月16日
	 * @return		: OauthClientDetails
	 * @param 		: clientId
	 */
	OauthClientDetails findByClientId(String clientId);
	
}
