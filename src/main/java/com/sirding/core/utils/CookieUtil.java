package com.sirding.core.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Described	: cookie工具类
 * @project		: com.sirding.core.utils.CookieUtil
 * @author 		: zc.ding
 * @date 		: 2016年12月29日
 */
public class CookieUtil {

	private static String DEFAULT_PATH = "/";		//
	private static int DEFAULT_MAX_AGE = 1200;		//默认20分钟
	
	public enum CookieType{
		DOMAIN,
		MAX_AGE,
		PATH;
	}
	
	/**
	 * @Described			: 创建cookie
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param response
	 * @param cookie
	 */
	public static void setCookie(HttpServletResponse response, Cookie cookie){
		response.addCookie(cookie);
	}
	
	/**
	 * @Described			: 创建cookie
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param name
	 * @param value
	 * @return
	 */
	public static Cookie getCookie(String name, String value){
		return getCookie(name, value, null, DEFAULT_MAX_AGE, DEFAULT_PATH);
	}
	
	/**
	 * @Described			: 创建cookie
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param 				：   name
	 * @param 				：   value
	 * @param 				：   obj
	 * @param 				：   type 属性类型
	 * @return
	 */
	public static Cookie getCookie(String name, String value, Object obj, CookieType type){
		switch (type) {
		case DOMAIN:
			return getCookie(name, value, (String)obj, DEFAULT_MAX_AGE, null);
		case MAX_AGE:
			return getCookie(name, value, null, (int)obj, null);
		case PATH:
			return getCookie(name, value, null, DEFAULT_MAX_AGE, (String)obj);
		default:
			break;
		}
		return null;
	}
	
	/**
	 * @Described			: 创建cookie
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param name
	 * @param value
	 * @param domain
	 * @param maxAge
	 * @param path
	 * @return
	 */
	public static Cookie getCookie(String name, String value, String domain, int maxAge, String path){
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(HttpSessionUtil.getRequest().getServerName());
		cookie.setPath(DEFAULT_PATH);
		if(domain != null && domain.length() > 0){
			cookie.setDomain(domain);
		}
		if(path != null && path.length() > 0){
			cookie.setPath(path);
		}
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	/**
	 * @Described			: 获得指定name的cookie
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(name != null && name.equals(cookie.getName())){
					return cookie;
				}
			}
		}
		return null;
	}
	
}
