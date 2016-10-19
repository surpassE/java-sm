package com.sirding.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sirding.core.utils.secure.PwdUtil;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired
	private RedisSessionDAO redisSessionDAO;
	
	@RequestMapping("/login")
	public String login(String userName, String pwd){
		Subject subject = SecurityUtils.getSubject();
		String password = PwdUtil.encrypt(pwd).toString();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		token.setRememberMe(true);
		boolean success = true;
		try {
			subject.login(token);
		} catch (AccountException e) {
			logger.debug("用户名或密码不正确");
			success = !success;
		}catch (CredentialsException e) {
			logger.debug("密码或密码不正确");
			success = !success;
		}catch (AuthenticationException e) {
			logger.debug("身份验证异常....");
			success = !success;
			e.printStackTrace();
		}
		if(!success){
			token.clear();
			redisSessionDAO.delete(subject.getSession());
			return "redirect:/auth/login.jsp";
		}
		return "shiro/home";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		Subject subject = SecurityUtils.getSubject();
		Session shiroSession = subject.getSession();
		String msg = (String)shiroSession.getAttribute("sirding");
		logger.debug("从shiro的sesion中取值：" + msg);
		subject.logout();
		return "redirect:/auth/login.jsp";
	}
}
