package com.sirding.core.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sirding.commons.Cons.UserType;
import com.sirding.core.utils.HttpSessionUtil;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUser;

public class SessionInterceptor implements HandlerInterceptor{

	Logger logger = Logger.getLogger(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return checkSessionByRedis(request, response, handler);
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
	boolean checkSession(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		AppSysUser sysUser = HttpSessionUtil.getAppSysUser();
		AppUser user = HttpSessionUtil.getAppUser();
		UserType userType = HttpSessionUtil.getUserType();
		boolean flag = checkCurrUser(userType, sysUser, user, response); 
		return flag;
	}
	
	/**
	 * @Described			: 通过redis判断session
	 * @author				: zc.ding
	 * @date 				: 2016年12月28日
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	boolean checkSessionByRedis(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		UserType userType = null;
		AppSysUser sysUser = null;
		AppUser user = null;
		userType = HttpSessionUtil.getUserTypeFromRedis();
		sysUser = HttpSessionUtil.getAppSysUserFromRedis();
		user = HttpSessionUtil.getAppUserFromRedis();
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
