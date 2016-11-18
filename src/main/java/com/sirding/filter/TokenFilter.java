package com.sirding.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.sirding.commons.Cons;
import com.sirding.core.utils.TokenUtil;

/**
 * @Described	: 添加token的filter每次响应都会创建token
 * @project		: com.sirding.filter.TokenFilter
 * @author 		: zc.ding
 * @date 		: 2016年11月18日
 */
public class TokenFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		String token = TokenUtil.getToken();
		request.setAttribute("resubmitToken", token);
		request.setAttribute(Cons.CSRF_TOKEN, token);
	}

	@Override
	public void destroy() {
		
	}

}
