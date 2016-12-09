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
	
	@Test
	public void addSysUser(){
		AppSysUser user = new AppSysUser();
		user.setLoginName("user" + System.currentTimeMillis());
		user.setLoginPwd("hello");
		user.setNote("test");
		user.setStatus(new Byte("1"));
		int i = this.appSysUserService.add(user);
		System.out.println(i);
	}

}
