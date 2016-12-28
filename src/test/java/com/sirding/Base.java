package com.sirding;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {
		"classpath:xml/spring-core.xml",
		"classpath:xml/spring-mybatis.xml",
		"classpath:xml/spring-security.xml",
		"classpath:xml/spring-redis.xml"
		}
)
public class Base {
	
	/**
	 * 打印华丽的分割线
	 * @date 2016年10月20日
	 * @author zc.ding
	 */
	public static void printLine(){
		System.out.println("========================华丽分割线====================================");
	}
}
