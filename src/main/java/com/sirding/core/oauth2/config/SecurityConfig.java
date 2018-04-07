package com.sirding.core.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.header.HeaderWriter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sirding.core.oauth2.CustDaoAuthenticationProvider;
import com.sirding.core.oauth2.CustOauth2AuthenticationEntryPoint;
import com.sirding.service.CustUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustUserDetailService custUserDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("mobile").password("mobile").roles("MOBILE");
		auth.userDetailsService(custUserDetailService);
		//创建盐源
		ReflectionSaltSource saltSource = new ReflectionSaltSource();
		saltSource.setUserPropertyToUse("username");
		//定义加密算法
		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
		
		DaoAuthenticationProvider authenticationProvider = new CustDaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(auth.getDefaultUserDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		authenticationProvider.setSaltSource(saltSource);
		auth.authenticationProvider(authenticationProvider);
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/static/**", "/images/**", "/oauth/uncache_approvals", "/oauth/cache_approvals",
				"/oauth", "/oauth*", "/oauthClient/**");
    }
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		.antMatchers("/oauth/**").permitAll().anyRequest().hasAnyRole("MOBILE").and().antMatcher("/**").anonymous()
		http.sessionManagement().disable().authorizeRequests()
		.antMatchers("/oauth/**").hasAnyRole("MOBILE")	//oauth下的资源访问需要MOBILE角色
		.and()
		.authorizeRequests().antMatchers("/**").denyAll()	//其他资源未通过oauth2验证的全部拒绝
		.and()
		.exceptionHandling().accessDeniedPage("/login.jsp?authorization_error=true")
		.and()
		.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable().logout().logoutUrl("/logout").logoutSuccessUrl("/login.jsp")
		.and()
		.formLogin().loginProcessingUrl("/adminLogin").failureUrl("/login.jsp").loginPage("/login.jsp").passwordParameter("pwd").usernameParameter("userName")
		.and();
	}
	
}
