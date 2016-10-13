package com.sirding.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	Logger logger = Logger.getLogger(AuthController.class);
	
	@RequestMapping("/login")
	public String login(String userName, String pwd){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, pwd);
		token.setRememberMe(true);
		boolean success = true;
		try {
			subject.login(token);
		} catch (AccountException e) {
			logger.debug("用户名不正确");
			success = !success;
		}catch (CredentialsException e) {
			logger.debug("密码不正确...");
			success = !success;
		}catch (AuthenticationException e) {
			logger.debug("身份验证异常....");
			success = !success;
		}
		if(!success){
			token.clear();
			return "redirect:/auth/login.jsp";
		}
		return "shiro/home";
	}
}
