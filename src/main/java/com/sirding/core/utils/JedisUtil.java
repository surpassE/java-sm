package com.sirding.core.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Described	: jedis工具类
 * @project		: com.sirding.core.utils.JedisUtil
 * @author 		: zc.ding
 * @date 		: 2016年11月29日
 */
public class JedisUtil {

	private static String host = "127.0.0.1";
	private static Integer port = 2185;
	//redis连接池
	private static JedisPool pool ;
	private JedisUtil(){}

	/**
	 * @Described	: 初始化jedis连接池
	 * @author		: zc.ding
	 * @date 		: 2016年11月29日
	 */
	private synchronized static void initJedisPool(){
		if (pool == null) {  
            JedisPoolConfig config = new JedisPoolConfig();  
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。  
            config.setMaxIdle(5);  
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；  
            config.setMaxWaitMillis(100 * 1000);;
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；  
            config.setTestOnBorrow(true); 
            pool = new JedisPool(config, host, port);
//            pool = new JedisPool(config, OtherInstance.newInstance().REDIS_IP,  Integer.parseInt(OtherInstance.newInstance().REDIS_PORT));
        }  
	}
	
	/**
	 * @Described			: 销毁连接池
	 * @author				: zc.ding
	 * @date 				: 2016年11月29日
	 */
	public static synchronized void destroy(){
		if(pool != null){
			pool.destroy();
		}
	}
	
	/**
	 * @Described	: 获得jedis实例
	 * @author		: zc.ding
	 * @date 		: 2016年11月29日
	 * @return
	 */
	private static Jedis getJedis(){
		if(pool == null){
			initJedisPool();
		}
		return pool.getResource();
	}
	
	/**
	 * @Described	: 将使用完的jedis实例换回到jedis连接池中
	 * @author		: zc.ding
	 * @date 		: 2016年11月29日
	 * @param jedis	: jedis实例
	 * @param broken: jeids异常标志
	 */
	private static void returnResource(Jedis jedis, boolean broken){
		if(jedis != null){
			if(broken){
				pool.returnBrokenResource(jedis);
			}else{
				pool.returnResource(jedis);
			}
		}
	}
	
	/**
	 * 
	 * @Described			: 向redis中添加属性值
	 * @author				: zc.ding
	 * @date 				: 2016年11月29日
	 * @param key			:
	 * @param value			:
	 */
	public static void addKey(String key, String value){
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
		}finally {
			returnResource(jedis, broken);
		}
	}
	
	/**
	 * @Described			: 删除reids中key及对应的值
	 * @author				: zc.ding
	 * @date 				: 2016年11月29日
	 * @param keys			: keys 类型：byte[] | String | (byte[]、String组合) 
	 */
	public static void delKey(Object... keys){
		Jedis jedis = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			if(keys != null){
				for(Object key : keys){
					//判断key的类型是不是byte[]类型
					if(key.getClass().equals(byte[].class.toString())){
						jedis.del((byte[])key);
					}
					//判断key的类型是不是String类型
					if(key.getClass().toString().equals(String.class.toString())){
						jedis.del((String)key);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
		}finally{
			returnResource(jedis, broken);
		}
	}
	
	
	/**
	 * @Described			: 从redis中获得指定key对应的数据信息
	 * @author				: zc.ding
	 * @date 				: 2016年11月29日
	 * @param key
	 * @return
	 */
	public static String getKey(String key){
		Jedis jedis = null;
		String value = null;
		boolean broken = false;
		try {
			jedis = getJedis();
			value = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			broken = true;
		}finally{
			returnResource(jedis, broken);
		}
		return value;
	}
}
