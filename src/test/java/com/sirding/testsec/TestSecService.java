package com.sirding.testsec;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirding.Base;
import com.sirding.mybatis.model.AppMenu;
import com.sirding.service.AppMenuService;
import com.sirding.service.SecService;

public class TestSecService extends Base{

	Logger logger = Logger.getLogger(TestSecService.class);
	@Autowired
	private SecService secService;
	@Autowired
	private AppMenuService appMenuService;
	
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
	
	
}


