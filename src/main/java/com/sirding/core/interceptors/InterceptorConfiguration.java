package com.sirding.core.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
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
