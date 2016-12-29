package com.sirding.core.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sirding.commons.Cons;
import com.sirding.commons.Cons.UserType;
import com.sirding.core.utils.CookieUtil;
import com.sirding.core.utils.HttpSessionUtil;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUser;

public class SessionInterceptor implements HandlerInterceptor{

	Logger logger = Logger.getLogger(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return checkSessionFromCookie(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.debug("interceptor请求之后...");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.debug("interceptor请求之后(已完结)...");
		
	}
	
	/**
	 * @Described			: 直接从request中获得HttpSession进而判断是否存在有效的session，此种连接超时的判断适用于如下情况<br/>
	 * 						  	1、单应用，
	 * 							2、分布式，nginx做为最外端代理服务器，且负载均衡中采用ip_hash的分流方式
	 * @author				: zc.ding
	 * @date 				: 2016年12月28日
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 */
	boolean checkSessionFromRequest(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AppSysUser sysUser = HttpSessionUtil.getAppSysUser();
		AppUser user = HttpSessionUtil.getAppUser();
		UserType userType = HttpSessionUtil.getUserType();
		boolean flag = checkCurrUser(userType, sysUser, user, response); 
		return flag;
	}
	
	/**
	 * @Described			: 通过sessionId和redis判断session<br/>
	 * 							1、单应用
	 * 							2、分布式(存在sessionId不一致问题，需要扩展tomcat源码，有待更新)
	 * @author				: zc.ding
	 * @date 				: 2016年12月28日
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	boolean checkSessionFromRedis(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		UserType userType = HttpSessionUtil.getUserTypeFromRedis();
		AppSysUser sysUser = HttpSessionUtil.getAppSysUserFromRedis();
		AppUser user = HttpSessionUtil.getAppUserFromRedis();
		return this.checkCurrUser(userType, sysUser, user, response);
	}
	
	/**
	 * @Described			: 通过cookie和redis验证session状态，应用于分布式系统
	 * 							1、单应用
	 * 							2、分布式(在用户禁用的cookie的情况下，存在问题，需扩展tomcat源码)
	 * @author				: zc.ding
	 * @date 				: 2016年12月29日
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	boolean checkSessionFromCookie(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		Cookie cookie = CookieUtil.getCookie(request, Cons.COOKIE_USER);
		String token = null;
		if(cookie != null){
			token = cookie.getValue();
		}
		UserType userType = HttpSessionUtil.getUserTypeFromRedis(token);
		AppSysUser sysUser = HttpSessionUtil.getAppSysUserFromRedis(token);
		AppUser user = HttpSessionUtil.getAppUserFromRedis(token);
		return this.checkCurrUser(userType, sysUser, user, response);
	}
	
	/**
	 * @Described			: 检查是否存有效的用户信息
	 * @author				: zc.ding
	 * @date 				: 2016年12月28日
	 * @param userType
	 * @param sysUser
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private boolean checkCurrUser(UserType userType, AppSysUser sysUser, AppUser user, HttpServletResponse response) throws Exception {
		logger.debug("当前用户：[" + sysUser + "]==[" + user + "]");
		boolean flag = true;
		String noSession = "nosession.jsp";
//		没有用户信息
		if(sysUser == null && user == null){
			flag = false;
		}
		if(userType != null){
			switch (userType) {
			case APP_SYS_USER:
				if(sysUser == null || sysUser.getLoginName() == null || sysUser.getLoginName().length() <= 0){
					noSession = "nosession.jsp";
					flag = false;
				}
				break;
			case APP_USER:
				if(user == null || user.getLoginName() == null || user.getLoginName().length() <= 0){
					noSession = "nosession.jsp";
					flag = false;
				}
				break;
			default:
				break;
			}
		}
		if(!flag){
			response.sendRedirect("/" + noSession);
		}
		return flag;
	}
	
	/**
	 * @Described			: 判断请求是不是ajax请求
	 * @author				: zc.ding
	 * @date 				: 2016年12月28日
	 * @param request
	 * @return
	 */
	boolean isAjaxRequest(HttpServletRequest request){
		String requestType = request.getHeader("X-Requested-With");
		if(requestType == null || requestType.length() <= 0){
			return false;
		}
		return true;
	}

}
