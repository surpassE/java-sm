package com.sirding.core.utils.json.jsonlib;

import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * net-json格式类型的数据域集合或是对象的相互转换
 * @author zc.ding
 * @date 2016年10月16日
 *
 */
public class JsonUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JsonUtil.class);

	/**
	 * Json格式字符串的数据转换为对象
	 * @param clazz
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObject(String data, Class<?> clazz){
		JSONObject json = JSONObject.fromObject(data);
		return (T) JSONObject.toBean(json,clazz);
	}

	/**
	 * 自定义对象含有自定义集合 异常类型：Property {1} not found on type net.sf.ezmorph.bean.MorphDynaBean
	 * @param clazz
	 * @param listClazz
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObject(String data, Class<?> clazz, Class<?> listClazz){
		//数据总list转化的类型
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("list",listClazz );
		JSONObject json = JSONObject.fromObject(data);
		return (T) JSONObject.toBean(json,clazz,classMap);
	}
	
	/**
	 * 对象转为Json格式的数据字符串 异常类型:There is a cycle in the hierarchy
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj){
		JsonConfig jsonConfig = new JsonConfig();
		//防止出现There is a cycle in the hierarchy异常，用于hibernate多对多的关系
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(obj, jsonConfig).toString();
	}

	/**
	 * 将对象中的arr数组中的属性过滤出去
	 * @param obj
	 * @param arr 需要过滤掉的数组信息
	 * @return
	 */
	public static String objectToJson(Object obj,String...arr){
		JsonConfig jsonConfig = new JsonConfig();
		//防止出现There is a cycle in the hierarchy异常，用于hibernate多对多的关系
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(arr);
		return JSONObject.fromObject(obj, jsonConfig).toString();
	}
	
	/**
	 * 将含有多对多或是外键的对象手动封装，序列化为JSON字符串
	 * @param obj	要格式化的对象
	 * @param exclude	要从对象中提出的属性字段
	 * @param map	外键及外键中要序列化的属性的字段
	 * @return
	 */
	public static String objectToJson(Object obj, String[] exclude, Map<Class<?>, String[]> map){
		JsonConfig jsonConfig = new JsonConfig();
		//去掉对象中指定的属性字段
		jsonConfig.setExcludes(exclude);
		Set<Class<?>> set = map.keySet();
		if(set != null && set.size() > 0){
			for(Class<?> clazz : set){
				//将外键对象手动进行格式装换、重新封装
				jsonConfig.registerJsonValueProcessor(clazz, new ObjectJsonValueProcessor(map.get(clazz), clazz));  
			}
		}
		logger.debug(JSONObject.fromObject(obj, jsonConfig).toString());
		return JSONObject.fromObject(obj, jsonConfig).toString();
	}
	
	/**
	 * json字符串转换为集合的接口类型
	 * @param clazz
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> jsonToCollection(String data, Class<?> clazz){
		JSONArray jsonObj = JSONArray.fromObject(data);  
		return JSONArray.toCollection(jsonObj,clazz);
	}
	
	/**
	 * 集合接口类型的数据转为json格式的字符串
	 * @param c
	 * @return
	 */
	public static String collectionToJson(Collection<?> c){
		JsonConfig jsonConfig = new JsonConfig();
		//防止出现There is a cycle in the hierarchy异常，用于hibernate多对多的关系
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONArray.fromObject(c,jsonConfig).toString();
	}
	
	/**
	 * map集合转为json格式的字符串
	 * The map into a json format string
	 * @param map
	 * @return
	 */
	public static String mapToJson(Map<String, ?> map){
		JsonConfig jsonConfig = new JsonConfig();
		//防止出现There is a cycle in the hierarchy异常，用于hibernate多对多的关系
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return JSONObject.fromObject(map,jsonConfig).toString();
	}
	
	/**
	 * json格式的字符串转为Map对象
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String,T> jsonToMap(String data, Class<T> clazz){
		JSONObject jsonObj = JSONObject.fromObject(data);  
        JsonConfig jsonCfg = new JsonConfig();  
        jsonCfg.setRootClass(Map.class);  
        Map<String,Object> classMap = new HashMap<String, Object>();  
        classMap.put(".*", clazz);  
        jsonCfg.setClassMap(classMap);  
        Map<String,T> map = (Map<String, T>)JSONObject.toBean(jsonObj, jsonCfg);
        return map;
	}
	
	
	
	public static void init(){
//		JsonConfig jsonConfig = new JsonConfig();
		//防止出现There is a cycle in the hierarchy异常，用于hibernate多对多的关系
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		//去掉对象中指定的属性字段
//		jsonConfig.setExcludes(new String[]{"TServerUserServers"});
		//将外键对象手动进行格式装换、重新封装
//		jsonConfig.registerJsonValueProcessor(TServerUser.class, new ObjectJsonValueProcessor(new String[]{"id"},TServerUser.class));
//		jsonConfig.registerJsonValueProcessor(TServer.class, new ObjectJsonValueProcessor(new String[]{"id"},TServer.class));  
//		String msg = JSONArray.fromObject(list,jsonConfig).toString();
	}
}
