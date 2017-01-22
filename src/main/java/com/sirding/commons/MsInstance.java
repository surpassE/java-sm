package com.sirding.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import com.sirding.core.utils.ACUtils;

/**
 * @Described	: 加载中间件配置，通过注解或是配置文件加载properties文件<br/>
 * 					@PropertySources是@PropertySource的集合，加载文件时会将所有的文件进行合并，再通过${}通过key进行读取
 * 					@PropertySource(value = { "classpath:properties/redis.properties" })使用时属性上通过注解@Value("${redis_ip}")加载文件内容<br/>
 * 					<util:properties id="redis" location="classpath:properties/other.properties" ignore-resource-not-found="true"/><br/>
 * 					使用时属性上通过注解@Value("#{redis.redis_ip}")加载文件内容<br/>
 * @project		: com.sirding.commons.MsInstance
 * @author 		: zc.ding
 * @date 		: 2016年12月8日
 */
@Component("msInstance")
//@PropertySource(value = { "classpath:properties/redis.properties" })
@PropertySources(value = { 
		@PropertySource(value = { "classpath:properties/ms.properties" },name = "ms" ),
		@PropertySource(value = { "classpath:properties/db.properties" },name = "db", ignoreResourceNotFound = true)
})
public class MsInstance {
	public final static Object INSTANCE_LOCK = new Object();
	private static MsInstance msInstance = null;

	/**
	 * @Described			: 实现单例
	 * @author				: zc.ding
	 * @date 				: 2016年12月8日
	 * @return
	 */
	public static MsInstance newInstance(){
		if(msInstance == null){
			synchronized (INSTANCE_LOCK) {
				if(msInstance == null){
					msInstance = ACUtils.getBean("msInstance", MsInstance.class);
				}
			}
		}
		return msInstance;
	}
	
	@Value("${jdbc_driver}")
	public String JDBC_DRIVER;
	//redis配置信息
	@Value("${redis.host}")
	public String REDIS_HOST;
	@Value("${redis.port}")
	public int REDIS_PORT;
	@Value("${redis.pass}")
	public String REDIS_PASS;
	@Value("${redis.dbIndex}")
	public int REDIS_DB_INDEX;
	@Value("${redis.expiration}")
	public int REDIS_EXPIRATION;
	@Value("${redis.maxIdle}")
	public int REDIS_MAXIDLE;
	@Value("${redis.maxActive}")
	public int REDIS_MAXACTIVE;
	@Value("${redis.maxWait}")
	public int REDIS_MAXWAIT;
	@Value("${redis.testOnBorrow}")
	public boolean REDIS_TESTONBORROW;

	//activemq
	@Value("${activeMq.url}")
	public String ACTIVEMQ_URL;
}
