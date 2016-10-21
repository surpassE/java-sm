package com.sirding.service;

import java.util.List;

import com.sirding.base.CurdService;
import com.sirding.mybatis.model.AppPerm;

/**
 * 应用权限管理接口
 * @author zc.ding
 * @date 2016年10月19日
 *
 */
public interface AppPermService extends CurdService<AppPerm> {
	
	/**
	 * 通过应用用户的userName查询对应的权限
	 * @param userName
	 * @return
	 * @author zc.ding
	 * @date 2016年10月21日
	 */
	List<AppPerm> findPermByUserName(String userName);
	
	/**
	 * 通过管理呀U你用户查询对应的权限
	 * @param userName
	 * @return
	 * @author zc.ding
	 * @date 2016年10月21日
	 */
	List<AppPerm> findPermBySysUserName(String userName);

}
