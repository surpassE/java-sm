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
		AppSysUser sysUser = HttpSessionUtil.getAppSysUser();
		AppUser user = HttpSessionUtil.getAppUser();
		logger.debug("当前用户：[" + sysUser + "]==[" + user + "]");
		boolean flag = true;
		String noSession = "nosession.jsp";
//		没有用户信息
		if(sysUser == null && user == null){
			flag = false;
		}
//		判断当前用户的类型
		UserType userType = HttpSessionUtil.getUserType();
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
			response.sendRedirect(noSession);
		}
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.debug("interceptor请求之后...");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.debug("interceptor请求之后(已完结)...");
		
	}

}
