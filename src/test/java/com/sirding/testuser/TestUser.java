package com.sirding.testuser;

import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sirding.mybatis.model.UserInfo;
import com.sirding.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={
		"classpath:spring-core.xml", 
		"classpath:spring-mybatis.xml"})  
public class TestUser {
	
	Logger logger = Logger.getLogger(TestUser.class);

	@Autowired
	private UserService userService;
	
	@Test
	public void testQuery(){
		UserInfo user = this.userService.findUser(1);
		assertNotNull(user);
		assertEquals("sirding", user.getName());
	}
	
	@Test
	public void testAdd(){
		UserInfo user = new UserInfo();
		user.setName("test" + System.currentTimeMillis());
		user.setAge(20);
		int i = this.userService.addUser(user);
		logger.debug("用户ID：" + user.getId());
		assertEquals(1, i);
//		try {
//			Thread.sleep(1000*60);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void testUpdate(){
		UserInfo user = this.userService.findUser(4);
		user.setName("sirding_hello");
		int i = this.userService.updateUser(user);
		assertEquals(1, i);
	}
	
	@Test
	public void testDel(){
		UserInfo user = new UserInfo();
		user.setId(2);
		int i = this.userService.delUser(user);
		assertEquals(i, i);
		
	}
}
