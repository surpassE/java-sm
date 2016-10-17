package com.sirding.core.security.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

import com.sirding.core.redis.JedisManager;


public class CustShiorCacheManager implements CacheManager, Destroyable{

	private JedisManager jedisManager;
	
	@Override
	public void destroy() throws Exception {
//		jedisManager.getJedis().shutdown();
		
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return new JedisShiroCache<>(name, jedisManager);
	}

}
