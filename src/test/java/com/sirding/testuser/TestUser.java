package com.sirding.testuser;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirding.Base;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.service.AppSysUserService;

public class TestUser extends Base{
	
	@Autowired
	private AppSysUserService appSysUserService;
	
	@Test
	public void test2(){
		AppSysUser user = this.appSysUserService.findById(1);
		System.out.println("name :" + user.getLoginName());
	}

}
