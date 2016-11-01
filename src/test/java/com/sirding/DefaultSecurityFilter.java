package com.sirding;

/**********************默认的security的12filter***********************************/

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


/**********************************************************************/
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/*********************************************************************/
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;


public class DefaultSecurityFilter {

	public void test(){

	
	}
}


//[DEBUG]/sec/toRole.htm at position 1 of 11 in additional filter chain; firing Filter: 'SecurityContextPersistenceFilter'
//[DEBUG]/sec/toRole.htm at position 2 of 11 in additional filter chain; firing Filter: 'WebAsyncManagerIntegrationFilter'
//[DEBUG]/sec/toRole.htm at position 3 of 11 in additional filter chain; firing Filter: 'HeaderWriterFilter'
//[DEBUG]/sec/toRole.htm at position 4 of 11 in additional filter chain; firing Filter: 'LogoutFilter'
//[DEBUG]/sec/toRole.htm at position 5 of 11 in additional filter chain; firing Filter: 'UsernamePasswordAuthenticationFilter'
//[DEBUG]/sec/toRole.htm at position 6 of 11 in additional filter chain; firing Filter: 'RequestCacheAwareFilter'
//[DEBUG]/sec/toRole.htm at position 7 of 11 in additional filter chain; firing Filter: 'SecurityContextHolderAwareRequestFilter'
//[DEBUG]/sec/toRole.htm at position 8 of 11 in additional filter chain; firing Filter: 'AnonymousAuthenticationFilter'
//[DEBUG]/sec/toRole.htm at position 9 of 11 in additional filter chain; firing Filter: 'SessionManagementFilter'
//[DEBUG]/sec/toRole.htm at position 10 of 11 in additional filter chain; firing Filter: 'ExceptionTranslationFilter'
//[DEBUG]/sec/toRole.htm at position 11 of 11 in additional filter chain; firing Filter: 'FilterSecurityInterceptor'

