package com.sirding.core.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.sirding.core.utils.LoggerUtils;

/**
 * 自定义logout成功handler，替换默认的{@linkorg.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler}
 * onLogoutSuccess中可以指定结束后重定向的页面，也可扩展自定以需求
 * @author zc.ding
 * @date 2016年10月29日
 *
 */
public class CustLogoutSuccessHandler extends
AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		LoggerUtils.debugForTest(getClass(), "执行自定的退出handler控制器...");
		//指定重定向的页面，默认为 "/"
		setDefaultTargetUrl("/auth/unauthorized.jsp");
		super.handle(request, response, authentication);
		
	}

}
