package com.sirding.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sirding.commons.Cons;
import com.sirding.commons.Cons.UserType;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUser;

/**
 * @Described	: HttpSession工具类
 * @project		: com.sirding.core.utils.HttpSessionUtil
 * @author 		: zc.ding
 * @date 		: 2016年12月29日
 */
public class HttpSessionUtil {
	private static Logger logger = Logger.getLogger(HttpSessionUtil.class);
	
	/**
	 * @Described			: 获得HttpServletResponse
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @return
	 */
	public static HttpServletResponse getResponse(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}
	
	/**
	 * @Described			: 获得HttpServletRequest
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * @Described	: 获得请求的session
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: HttpSession
	 * @return
	 */
	public static HttpSession getSession(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}
	
	/**
	 * @Described	: 从session中获得AppUser对象
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: AppUser
	 */
	public static AppUser getAppUser(){
		return (AppUser)getSession().getAttribute(Cons.UserType.APP_USER.name());
	}
	
	/**
	 * @Described			: 从session中获得AppUser对象
	 * @author				: zc.ding
	 * @date 				: 2016年12月28日
	 * @param session		: 指定session
	 * @return				: AppUser
	 */
	public static AppUser getAppUser(HttpSession session){
		return (AppUser)session.getAttribute(Cons.UserType.APP_USER.name());
	}
	
	/**
	 * 
	 * @Described	: 从session获得AppSysUser对象
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: AppSysUser
	 */
	public static AppSysUser getAppSysUser(){
		return (AppSysUser)getSession().getAttribute(Cons.UserType.APP_SYS_USER.name());
	}
	
	/**
	 * @Described			: 从session获得AppSysUser对象
	 * @author				: zc.ding
	 * @date 				: 2016年12月28日
	 * @param session		: 指定session
	 * @return				: AppSysUser
	 */
	public static AppSysUser getAppSysUser(HttpSession session){
		return (AppSysUser)session.getAttribute(Cons.UserType.APP_SYS_USER.name());
	}
	
	/**
	 * @Described	: 保存AppUser信息到seesion中
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: void
	 * @param appUser
	 */
	public static void saveAppUser(AppUser appUser){
		getSession().setAttribute(Cons.UserType.APP_USER.name(), appUser);
		getSession().setAttribute(Cons.USER_TYPE, Cons.UserType.APP_USER);
		
	}

	/**
	 * @Described	: 存AppSysUser信息到seesion中
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: void
	 * @param appSysUser
	 */
	public static void saveAppSysUser(AppSysUser appSysUser){
		getSession().setAttribute(Cons.UserType.APP_SYS_USER.name(), appSysUser);
		getSession().setAttribute(Cons.USER_TYPE, Cons.UserType.APP_SYS_USER);
	}
	
	/**
	 * @Described			: 获得当前用户的类型
	 * @author				: zc.ding
	 * @date 				: 2016年12月27日
	 * @return
	 */
	public static UserType getUserType(){
		return (UserType)getSession().getAttribute(Cons.USER_TYPE);
	}
	
	/**
	 * @Described			: 获得当前用户的类型
	 * @author				: zc.ding
	 * @date 				: 2016年12月28日
	 * @param session
	 * @return
	 */
	public static UserType getUserType(HttpSession session){
		return (UserType)session.getAttribute(Cons.USER_TYPE);
	}
	
	/**
	 * @Described			: 保存应用用户信息到redis中
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param appUser
	 * @param keys
	 */
	public static void saveAppUserToRedis(AppUser appUser, String...keys){
		String key = getCustkey(keys);
		JedisUtil.addValue(key, Cons.UserType.APP_USER.name());
		JedisUtil.addObject(key + "_" + Cons.UserType.APP_USER.name(), appUser);
	}
	
	/**
	 * @Described			: 保存管理员用户信息到redis中
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param appSysUser
	 * @param keys
	 */
	public static void saveAppSysUserToRedis(AppSysUser appSysUser, String...keys){
		String key = getCustkey(keys);
		JedisUtil.addValue(key, Cons.UserType.APP_SYS_USER.name());
		JedisUtil.addObject(key + "_" + Cons.UserType.APP_SYS_USER.name(), appSysUser);
	}
	
	/**
	 * @Described			: 从redis中获得管理用户信息
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param keys
	 * @return
	 */
	public static AppSysUser getAppSysUserFromRedis(String...keys){
		String key = getCustkey(keys);
		logger.debug("获得管理用户的key:【" + key + "】");
		return JedisUtil.getObject(key + "_" + Cons.UserType.APP_SYS_USER.name(), AppSysUser.class);
	}
	
	/**
	 * @Described			: 从redis中获得应用用户信息
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param keys
	 * @return
	 */
	public static AppUser getAppUserFromRedis(String...keys){
		String key = getCustkey(keys);
		logger.debug("获得应用用户的key:【" + key + "】");
		return JedisUtil.getObject(key + "_" + Cons.UserType.APP_USER.name(), AppUser.class);
	}
	
	/**
	 * @Described			: 从redis中获得用户类型
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param keys
	 * @return
	 */
	public static UserType getUserTypeFromRedis(String...keys){
		String key = getCustkey(keys);
		logger.debug("获得用户类型的key:【" + key + "】");
		String userType = JedisUtil.getValue(key);
		if(userType == null){
			return null;
		}
		return Cons.UserType.valueOf(userType);
	}
	
	/**
	 * @Described			: 获得自定义的key
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param keys
	 * @return
	 */
	private static String getCustkey(String...keys) {
		String key = getSession().getId();
		if(keys != null && keys.length > 0){
			key = keys[0];
		}
		return key;
	}
}
