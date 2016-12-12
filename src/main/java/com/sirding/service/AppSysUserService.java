package com.sirding.service;

import com.sirding.base.CurdService;
import com.sirding.domain.PageAdapter;
import com.sirding.mybatis.model.AppSysUser;

/**
 * 应用后台管理员信息管理接口
 * @author zc.ding
 * @date 2016年10月20日
 *
 */
public interface AppSysUserService extends CurdService<AppSysUser> {
	
	/**
	 * @Described	: 统计数据总条数
	 * @author		: zc.ding
	 * @date 		: 2016年11月23日
	 * @return		: long
	 * @param record
	 * @return
	 */
//	long findSysUserCount(AppSysUser record);
	
	/**
	 * @Described	: 分页条件检索系统管理员信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月23日
	 * @return		: PageAdapter<AppSysUser>
	 * @param page
	 * @param record
	 * @return
	 */
	PageAdapter<AppSysUser> findSysUser(PageAdapter<AppSysUser> page, AppSysUser record);
	
}
