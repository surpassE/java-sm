package com.sirding.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class MyShiroFilter implements Filter{

	Logger logger = Logger.getLogger(MyShiroFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("自定义shiroFilter开始...");
		chain.doFilter(request, response);
		logger.info("自定义shiroFilter结束...");
		
	}

	@Override
	public void destroy() {
		
	}

}
