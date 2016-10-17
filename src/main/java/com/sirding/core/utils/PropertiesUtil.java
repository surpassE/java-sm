package com.sirding.core.utils;

import java.util.ResourceBundle;

/**
 * key=value文件操作工具类
 * @author surpassE
 *	
 */
public class PropertiesUtil {

	private static ResourceBundle bundle = null;
	private static final String commonPath = "jtsec_com";
	
	static{
		if(bundle == null){
			bundle = java.util.ResourceBundle.getBundle(commonPath);
		}
	}
	
	/**
	 * 重新加载数据信息
	 */
	public static void reloadBundle(){
		bundle = java.util.ResourceBundle.getBundle(commonPath);
	}
	
	/**
	 * 通过key获得配置文件中的value值
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return bundle.getString(key);
	}
}
