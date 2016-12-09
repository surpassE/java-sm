package com.sirding.core.redis;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

/**
 * @Described	: 自定义redis的key的生成算法
 * @project		: com.sirding.core.redis.RedisKeyGenerator
 * @author 		: zc.ding
 * @date 		: 2016年12月9日
 */
public class RedisKeyGenerator implements KeyGenerator{
	
	@Override
	public Object generate(Object target, Method method, Object... objects) {
		StringBuilder sb = new StringBuilder();  
        sb.append(target.getClass().getName());  
        sb.append(method.getName());  
        for (Object obj : objects) {  
            sb.append(obj.toString());  
        }  
        return sb.toString();
	}  
}
