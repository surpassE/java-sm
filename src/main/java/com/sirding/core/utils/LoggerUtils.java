package com.sirding.core.utils;

import org.apache.log4j.Logger;
/**
 * logger日志输出工具类
 * @author zc.ding
 * @date 2016年10月20日
 */
public class LoggerUtils {
	/**
	 * 是否开启Debug
	 */
	public static boolean isDebug =  Logger.getLogger(LoggerUtils.class).isDebugEnabled();
	
	/**
	 * Debug 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void debugForTest(Class<? extends Object> clazz ,Object message){
		if(!isDebug)return ;
		Logger logger = Logger.getLogger(clazz);
		logger.debug("=================华丽的分割线=======================");
		logger.debug(message);
		logger.debug("=================华丽的分割线=======================");
	}
	
	/**
	 * 打印华丽的分割线
	 * @param clazz
	 * @author zc.ding
	 * @date 2016年10月23日
	 */
	public static void line(Class<? extends Object> clazz){
		Logger logger = Logger.getLogger(clazz);
		logger.debug("======================华丽的分割线=============================================");
	}
	
	/**
	 * Debug 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void debug(Class<? extends Object> clazz ,String message){
		if(!isDebug)return ;
		Logger logger = Logger.getLogger(clazz);
		logger.debug(message);
	}
	
	/**
	 * Debug 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void info(Class<? extends Object> clazz ,String message){
		if(!isDebug)return ;
		Logger logger = Logger.getLogger(clazz);
		logger.info(message);
	}
	
	/**
	 * Debug 输出
	 * @param clazz  	目标.Class
	 * @param fmtString 输出信息key
	 * @param value		输出信息value
	 */
	public static void fmtDebug(Class<? extends Object> clazz,String fmtString,Object...value){
		if(!isDebug)return ;
		if(isBlank(fmtString)){
			return ;
		}
		if(null != value && value.length != 0){
			fmtString = String.format(fmtString, value);
		}
		debug(clazz, fmtString);
	}
	/**
	 * Error 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 * @param e			异常类
	 */
	public static void error(Class<? extends Object> clazz ,String message, Exception e){
		Logger logger = Logger.getLogger(clazz);
		if(null == e){
			logger.error(message);
			return ;
		}
		logger.error(message, e);
	}
	/**
	 * Error 输出
	 * @param clazz  	目标.Class
	 * @param message	输出信息
	 */
	public static void error(Class<? extends Object> clazz ,String message){
		error(clazz, message, null);
	}
	/**
	 * 异常填充值输出
	 * @param clazz 	目标.Class
	 * @param fmtString	输出信息key
	 * @param e			异常类
	 * @param value		输出信息value
	 */
	public static void fmtError(Class<? extends Object> clazz,Exception e,String fmtString, Object...value){
		if(isBlank(fmtString)){
			return ;
		}
		if(null != value && value.length != 0){
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString, e);
	}
	/**
	 * 异常填充值输出
	 * @param clazz		目标.Class
	 * @param fmtString 输出信息key
	 * @param value		输出信息value
	 */
	public static void fmtError(Class<? extends Object> clazz,
			String fmtString, Object...value) {
		if(isBlank(fmtString)){
			return ;
		}
		if(null != value && value.length != 0){
			fmtString = String.format(fmtString, value);
		}
		error(clazz, fmtString);
	}
	
	private static boolean isBlank(Object... objects){
		Boolean result = false ;
		for (Object object : objects) {
			if(null == object || "".equals(object.toString().trim()) 
					|| "null".equals(object.toString().trim())){
				result = true ; 
				break ; 
			}
		}
		return result ;
	}
	
}
