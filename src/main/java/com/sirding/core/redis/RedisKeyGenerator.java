package com.sirding.core.redis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.cache.interceptor.KeyGenerator;

import com.sirding.domain.PageAdapter;
import com.sirding.domain.dtpage.Page;

/**
 * @Described	: 自定义redis的key的生成算法
 * @project		: com.sirding.core.redis.RedisKeyGenerator
 * @author 		: zc.ding
 * @date 		: 2016年12月9日
 */
public class RedisKeyGenerator implements KeyGenerator{
	private final Logger logger = Logger.getLogger(getClass());
	
	@Override
	public Object generate(Object target, Method method, Object... objects) {
		long start = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();  
        sb.append(target.getClass().getName());  
        sb.append(method.getName());  
        sb.append(getParamValue(objects));
        logger.debug("生成redis的KEY耗时[" + (System.currentTimeMillis() - start) + "]毫秒");
        logger.debug(sb.toString());
        return sb.toString().hashCode();
	}
	
	/**
	 * @Described			: 获得参数的值
	 * @author				: zc.ding
	 * @date 				: 2016年12月11日
	 * @return				: String
	 * @param objects
	 * @return
	 */
	public String getParamValue(Object... objects){
		StringBuffer sb = new StringBuffer();
		if(objects != null && objects.length > 0){
			for(Object object : objects){
				switch (getType(object)) {
				case "type_base":
					sb.append(String.valueOf(object));
					break;
				case "type_pageAdpater":
					sb.append(getPageAdpate(object));
					break;
				default:
					break;
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * @Described			: 获得分页中每个参数的值
	 * @author				: zc.ding
	 * @date 				: 2016年12月11日
	 * @return				: String
	 * @param object
	 * @return
	 */
	private String getPageAdpate(Object object){
		StringBuffer sb = new StringBuffer();
		Field[] fields = object.getClass().getDeclaredFields();
		if(fields != null ){
			for(Field field : fields){
				Object obj;
				try {
					field.setAccessible(true);
					obj = field.get(object);
					if(obj != null && "type_base".equals(getType(obj))){
						sb.append(String.valueOf(obj));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					field.setAccessible(false);
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * @Described			: 判断对象类型
	 * @author				: zc.ding
	 * @date 				: 2016年12月11日
	 * @return				: String
	 * @param object
	 * @return
	 */
	private String getType(Object object){
		String type = "base_type";
		if(object instanceof java.lang.Integer || 
				object instanceof java.lang.Integer ||
				object instanceof java.lang.Boolean ||
				object instanceof java.lang.Short || 
				object instanceof java.lang.Long || 
				object instanceof java.lang.Double || 
				object instanceof Character || 
				object instanceof Byte || 
				object instanceof Float || 
				object instanceof String || 
				object instanceof Date){
			type = "type_base";
		}else if(object instanceof PageAdapter){
			type = "type_pageAdpater";
		}
		return type;
	}
	
	public static void main(String[] args) {
		RedisKeyGenerator kg = new RedisKeyGenerator();
		long start = System.currentTimeMillis();
		System.out.println(kg.getType(new Page<Object>()));
		System.out.println("耗时 + " + (System.currentTimeMillis() - start));
	}
}
