package com.sirding.core.interceptors;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Described	: <span>@EnableWebMvc</span>会导致单元测试的出现异常，因此将拦截器配置移入到spring-mvc.xml文件中进行配置
 * @project		: com.sirding.core.interceptors.InterceptorConfiguration
 * @author 		: zc.ding
 * @date 		: 2017年1月13日
 */
//@Configuration
//@EnableWebMvc
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/static/**")
        .excludePathPatterns("/login.jsp")
        .excludePathPatterns("/nosession.jsp")
        .excludePathPatterns("/adminLogin")
        .excludePathPatterns("/regist.jsp");
    }
}
