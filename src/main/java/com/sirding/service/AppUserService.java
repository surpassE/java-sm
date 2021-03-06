package com.sirding.service;

import com.sirding.base.CurdService;
import com.sirding.domain.PageAdapter;
import com.sirding.mybatis.model.AppUser;

public interface AppUserService extends CurdService<AppUser>{
	
	/**
	 * @Described	: 统计满足条件的应用用户的数据条数
	 * @author		: zc.ding
	 * @date 		: 2016年11月23日
	 * @return		: long
	 * @param record
	 * @return
	 */
//	long findUserCount(AppUser record);
	
	/**
	 * @Described	: 分页条件检索应用用户信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月23日
	 * @return		: PageAdapter<AppUser>
	 * @param page
	 * @param record
	 * @return
	 */
	PageAdapter<AppUser> findUser(PageAdapter<AppUser> page, AppUser record);

}
