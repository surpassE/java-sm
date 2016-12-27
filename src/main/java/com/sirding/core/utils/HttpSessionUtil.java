package com.sirding.core.utils;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sirding.commons.Cons;
import com.sirding.commons.Cons.UserType;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUser;

public class HttpSessionUtil {
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
	 * @return
	 */
	public static AppUser getAppUser(){
		return (AppUser)getSession().getAttribute(Cons.UserType.APP_USER.name());
	}
	
	/**
	 * 
	 * @Described	: 从session获得AppSysUser对象
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: AppSysUser
	 * @return
	 */
	public static AppSysUser getAppSysUser(){
		return (AppSysUser)getSession().getAttribute(Cons.UserType.APP_SYS_USER.name());
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
}
