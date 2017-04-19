package com.sirding.testutil;


import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import com.sirding.Msg;
import com.sirding.commons.MsInstance;
import com.sirding.core.utils.JedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
/**
 * @Described	: 测试jedisUtil工具类
 * @project		: com.sirding.testutil.TestJedisUtil
 * @author 		: zc.ding
 * @date 		: 2016年11月29日
 */
public class TestJedisUtil{

	Logger logger = Logger.getLogger(getClass());
	private static JedisPool pool;
	private static Jedis jedis;
	static RedisConnection connection;
	
	@BeforeClass
	public static void before(){
		if (pool == null) {  
			try {
				 JedisPoolConfig config = new JedisPoolConfig();  
		            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
		            config.setMaxIdle(300);  
		            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
		            config.setMaxWaitMillis(1000);
		            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
		            config.setTestOnBorrow(true); 
		            pool = new JedisPool(config, "127.0.0.1", 2185);
		            
		            jedis = pool.getResource();
		            JedisConnectionFactory jcf = new JedisConnectionFactory(config);
		            JedisShardInfo shardInfo = new JedisShardInfo("127.0.0.1", 2185);
		            jcf.setShardInfo(shardInfo);
		            jcf.setUsePool(true);
		            jcf.setHostName("127.0.0.1");
		            jcf.setPort(2185);
		            connection = jcf.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
            
		}  
	}
	
	@AfterClass
	public static void after(){
		JedisUtil.destroy();
	}
	
//	==================测试redis的lock===================
	
	@Test
	public void testLock(){
		String key = "zc.ding";
		String value = "hello";
		jedis.set(key, value);
		jedis.expire(key, 100);
		System.out.println("okok....");
	}
	
	@Test
	public void testLock1()	{
		String key = "zc.ding";
		String value = "hello";
		boolean flag = connection.setNX(key.getBytes(), value.getBytes());
		System.out.println(flag);
		flag = connection.setNX(key.getBytes(), value.getBytes());
		System.out.println(flag);
		jedis.expire(key, 100);
		System.out.println("okok....");
	}
	
	@Test
	public void freeLock(){
		String key = "zc.ding";
		jedis.del(key);
		System.out.println("okok...");
	}
	
	
	
//	==================测试redis的lock===================
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
