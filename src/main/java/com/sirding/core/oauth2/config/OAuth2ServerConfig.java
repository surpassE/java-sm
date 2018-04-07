package com.sirding.core.oauth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.sirding.core.oauth2.CustJdbcClientDetailsService;

@Configuration
public class OAuth2ServerConfig {

	/**
	 * @Description   : 资源服务,多个资源服务配置多个MobileResourceServerConfig即可
	 * @Project       : hk-api-services
	 * @Program Name  : com.yirun.finance.api.oauth.OAuth2ServerConfig.java
	 * @Author        : zhichaoding@hongkun.com zc.ding
	 */
	@Configuration
	@EnableResourceServer
	protected static class MobileResourceServerConfig extends ResourceServerConfigurerAdapter {
		private static final String DEFAULT_RESOURCE_ID = "mobile-resource";
		
		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.resourceId(DEFAULT_RESOURCE_ID).stateless(false);
		}
		
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
			.and()
//			.authorizeRequests().antMatchers("/oauth/token").fullyAuthenticated()
//			.and()
//			.authorizeRequests().antMatchers("/oauth").anonymous()
//			.and()
			.authorizeRequests().antMatchers("/user/**").hasRole("MOBILE")
			.and()
			.authorizeRequests().antMatchers("/**").denyAll();
//			.antMatchers("/user/**").access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_MOBILE'))");
//			http.addFilterBefore(filter, AbstractPreAuthenticatedProcessingFilter.class);
		}
	}
	
	/**
	 * @Description   : 授权服务器
	 * @Project       : java-sm
	 * @Program Name  : com.sirding.core.oauth2.config.OAuth2ServerConfig.java
	 * @Author        : zhichaoding@hongkun.com zc.ding
	 */
	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private DataSource dataSource;

		@Autowired
		private TokenStore tokenStore;
		
		@Autowired
		private CustJdbcClientDetailsService custJdbcClientDetailsService;

//		@Autowired
//		private UserApprovalHandler userApprovalHandler;

		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;

		@Value("${tonr.redirect:http://localhost:8080/tonr2/sparklr/redirect}")
		private String tonrRedirectUri;

		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.withClientDetails(this.custJdbcClientDetailsService);
			// @formatter:off
//			clients.inMemory()
//			.withClient("tonr-with-redirect")
//			.resourceIds(DEFAULT_RESOURCE_ID)
//			.authorizedGrantTypes("authorization_code", "implicit")
//			.authorities("ROLE_CLIENT")
//			.scopes("read", "write")
//			.secret("secret")
//			.redirectUris(tonrRedirectUri)
//			.and()
//			.withClient("my-trusted-client")
//			.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//			.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//			.scopes("read", "write", "trust")
//			.accessTokenValiditySeconds(60)
//			.and()
//			.withClient("my-trusted-client-with-secret")
//			.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//			.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//			.scopes("read", "write", "trust")
//			.secret("somesecret")
//			.and()
//			.withClient("my-less-trusted-client")
//			.authorizedGrantTypes("authorization_code", "implicit")
//			.authorities("ROLE_CLIENT")
//			.scopes("read", "write", "trust")
//			.and()
//			.withClient("mobile-client")
//			.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//			.secret("mobile")
//			.authorities("ROLE_MOBILE")
//			.scopes("read", "write", "trust")
//			.accessTokenValiditySeconds(600)
//			.and()
//			.withClient("my-less-trusted-autoapprove-client")
//			.authorizedGrantTypes("implicit")
//			.authorities("ROLE_CLIENT")
//			.scopes("read", "write", "trust")
//			.autoApprove(true);
			// @formatter:on
		}

		@Bean
		public TokenStore tokenStore() {
			return new JdbcTokenStore(dataSource);
		}
		
		@Bean
		public CustJdbcClientDetailsService custJdbcClientDetailsService() {
			return new CustJdbcClientDetailsService(dataSource);
		}
		
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.tokenStore(tokenStore)
			.authenticationManager(authenticationManager).allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET);
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer.allowFormAuthenticationForClients(); 
		}

	}

}

