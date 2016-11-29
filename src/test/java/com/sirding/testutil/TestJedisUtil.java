package com.sirding.testutil;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;

import com.sirding.core.utils.JedisUtil;
/**
 * @Described	: 测试jedisUtil工具类
 * @project		: com.sirding.testutil.TestJedisUtil
 * @author 		: zc.ding
 * @date 		: 2016年11月29日
 */
public class TestJedisUtil {

	Logger logger = Logger.getLogger(getClass());
	
	@AfterClass
	public static void after(){
		JedisUtil.destroy();
	}
	
	@Test
	public void test1(){
		String key = "zcding";
		JedisUtil.addKey("zcding", "hello world");
		String msg = JedisUtil.getKey(key);
		logger.debug(msg);
	}
	
	@Test
	public void test2(){
		String key = "zcding";
		String msg = JedisUtil.getKey(key);
		logger.debug(msg);
		JedisUtil.delKey(key);
		msg = JedisUtil.getKey(key);
		logger.debug(msg);
	}
	
	
}
