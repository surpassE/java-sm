package com.sirding.service;

import java.util.List;

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
	 * @return		: List<AppUser>
	 * @param page
	 * @param record
	 * @return
	 */
	List<AppUser> findUser(PageAdapter page, AppUser record);

}
