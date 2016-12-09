package com.sirding.core.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * @Described	: 通过代码开启Spring缓存的支持,同spring-redis.xml中的如下代码效果相同,二者选一<br/>
 * 					<cache:annotation-driven 
						cache-manager="redisCacheManager" 
						key-generator="redisKeyGenerator"/>
 * @project		: com.sirding.core.redis.RedisCacheConfig
 * @author 		: zc.ding
 * @date 		: 2016年12月9日
 */
//@Configuration("redisCacheConfig")
//@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport{

	private volatile JedisConnectionFactory jedisConnectionFactory;  
	private volatile RedisTemplate<String, String> redisTemplate;  
	private volatile RedisCacheManager redisCacheManager; 
	private volatile KeyGenerator redisKeyGenerator; 

	public RedisCacheConfig() {  
		super();  
	}  

	public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String,String> redisTemplate,  
			RedisCacheManager redisCacheManager) {  
		super();  
		this.jedisConnectionFactory = jedisConnectionFactory;  
		this.redisTemplate = redisTemplate;  
		this.redisCacheManager = redisCacheManager;  
	}
	
	public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory, RedisTemplate<String,String> redisTemplate,  
			RedisCacheManager redisCacheManager, KeyGenerator redisKeyGenerator) {  
		super();  
		this.jedisConnectionFactory = jedisConnectionFactory;  
		this.redisTemplate = redisTemplate;  
		this.redisCacheManager = redisCacheManager;
		this.redisKeyGenerator = redisKeyGenerator;
	}

	public JedisConnectionFactory redisConnectionFactory() {  
		return jedisConnectionFactory;  
	}  

	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {  
		return redisTemplate;  
	}  

	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {  
		return redisCacheManager;  
	}  

	@Override
	public CacheManager cacheManager() {
		return this.redisCacheManager;
	}

	@Override
	public KeyGenerator keyGenerator() {
		return this.redisKeyGenerator;
	}
}
