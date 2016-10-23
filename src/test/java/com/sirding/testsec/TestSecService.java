package com.sirding.testsec;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirding.Base;
import com.sirding.core.utils.LoggerUtils;
import com.sirding.mybatis.model.AppMenu;
import com.sirding.mybatis.model.AppPerm;
import com.sirding.service.AppMenuService;
import com.sirding.service.AppPermService;
import com.sirding.service.SecService;

public class TestSecService extends Base{

	Logger logger = Logger.getLogger(TestSecService.class);
	@Autowired
	private SecService secService;
	@Autowired
	private AppMenuService appMenuService;
	@Autowired
	private AppPermService appPermService;
	
	@Test
	public void test(){
//		AppMenu appMenu = this.appMenuService.findById(1);
//		logger.debug(appMenu.getName());
		List<AppMenu> list = this.secService.findMenuListByUserId(1);
		if(list != null){
			for(AppMenu menu : list){
				logger.debug(menu.getName());
			}
		}
	}
	
	@Test
	public void testQuery(){
		List<AppPerm> list = this.appPermService.findPermBySysUserName("admin");
		list = this.appPermService.findPermByUserName("sirding");
		LoggerUtils.line(getClass());
		if(list != null){
			for(AppPerm ap : list){
				logger.debug(ap.getWildcard());
			}
		}
		LoggerUtils.line(getClass());
	}
	
	
}


