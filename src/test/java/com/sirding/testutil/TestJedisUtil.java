package com.sirding.testutil;


import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;

import com.sirding.Base;
import com.sirding.Msg;
import com.sirding.commons.MsInstance;
import com.sirding.core.utils.JedisUtil;
/**
 * @Described	: 测试jedisUtil工具类
 * @project		: com.sirding.testutil.TestJedisUtil
 * @author 		: zc.ding
 * @date 		: 2016年11月29日
 */
public class TestJedisUtil extends Base{

	Logger logger = Logger.getLogger(getClass());
	
	@AfterClass
	public static void after(){
		JedisUtil.destroy();
	}
	
	@Test
	public void test1(){
		String key = "zcding";
		JedisUtil.addValue("zcding", "hello world");
		String msg = JedisUtil.getValue(key);
		logger.debug(msg);
	}
	
	@Test
	public void test2(){
		String key = "zcding";
		String msg = JedisUtil.getValue(key);
		logger.debug(msg);
		JedisUtil.delKey(key);
		msg = JedisUtil.getValue(key);
		logger.debug(msg);
	}
	
	@Test
	public void test3(){
		System.out.println(MsInstance.newInstance().REDIS_PORT);
		System.out.println(MsInstance.newInstance().REDIS_TESTONBORROW);
//		System.out.println(MsInstance.REDIS_IP);
		System.out.println(MsInstance.newInstance().JDBC_DRIVER);
		System.out.println(MsInstance.newInstance().ACTIVEMQ_URL);
	}
	
	@Test
	public void test4(){
		Msg msg = new Msg("zc.ding", 11);
		JedisUtil.addObject("msg", msg);
		Msg obj = JedisUtil.getObject("msg", Msg.class);
		logger.debug(obj.getName());
	}
	
	
}
