package com.sirding.service;

import java.util.List;

import com.sirding.mybatis.model.AppMenu;
import com.sirding.mybatis.model.AppPerm;
import com.sirding.mybatis.model.AppRole;

public interface SecService {

	/**
	 * 通过角色id检索菜单列表
	 * @date 2016年10月20日
	 * @author zc.ding
	 * @param id
	 * @return
	 */
	List<AppMenu> findMenuListByRoleId(Integer id);
	
	/**
	 * 通过用户id检索菜单列表
	 * @date 2016年10月20日
	 * @author zc.ding
	 * @param id
	 * @return
	 */
	List<AppMenu> findMenuListByUserId(Integer id);
	
	/**
	 * 通过角色id检索权限信息
	 * @date 2016年10月20日
	 * @author zc.ding
	 * @param id
	 * @return
	 */
	List<AppPerm> findPermListByRoleId(Integer id);
	
	/**
	 * 通过用户id检索对应权限信息
	 * @date 2016年10月20日
	 * @author zc.ding
	 * @param id
	 * @return
	 */
	List<AppPerm> findPermListByUserId(Integer id);
	
	/**
	 * 
	 * @date 2016年10月20日
	 * @author zc.ding
	 * @param id
	 * @return
	 */
	List<AppMenu> findMenuListBySysUserId(Integer id);
	
	/**
	 * 通过用户id检索对应的角色
	 * @date 2016年10月20日
	 * @author zc.ding
	 * @param id
	 * @return
	 */
	List<AppRole> findRoleListByUserId(Integer id);
}
