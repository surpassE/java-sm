package com.sirding.service;

import java.util.List;

import com.sirding.base.CurdService;
import com.sirding.mybatis.model.AppRole;

/**
 * 应用角色管理接口
 * @author zc.ding
 * @date 2016年10月19日
 *
 */
public interface AppRoleService extends CurdService<AppRole> {
	
	/**
	 * 通过用户userName查询对应的角色
	 * @param userName
	 * @return
	 * @author zc.ding
	 * @date 2016年10月21日
	 */
	List<AppRole> findRoleByUserName(String userName);
	
	/**
	 * 通过管理员userName查询对应的角色
	 * @param userName
	 * @return
	 * @author zc.ding
	 * @date 2016年10月21日
	 */
	List<AppRole> findRoleBySysUserName(String userName);
}
