package com.sirding.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sirding.commons.Cons;
import com.sirding.commons.Cons.UserType;
import com.sirding.core.shiro.CustTokenManager;
import com.sirding.core.utils.secure.PwdUtil;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	Logger logger = Logger.getLogger(AuthController.class);

	/**
	 * 验证应用用户信息
	 * @date 2016年10月21日
	 * @author zc.ding
	 * @param userName
	 * @param pwd
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String userName, String pwd){
		if(!this.authStatus(userName, pwd, UserType.APP_USER)){
			return "shiro/home";
		}
		return "shiro/home";
	}
	
	/**
	 * 系统管理员验证验证操作
	 * @date 2016年10月21日
	 * @author zc.ding
	 * @param userName
	 * @param pwd
	 * @return
	 */
	@RequestMapping("/adminLogin")
	public String adminLogin(String userName, String pwd){
//		if(!this.authStatus(userName, pwd, UserType.APP_SYS_USER)){
//			return "redirect:/auth/login.jsp";
//		}
		return "shiro/home";
	}
	
	/**
	 * 执行验证状态
	 * @date 2016年10月21日
	 * @author zc.ding
	 * @param userName
	 * @param pwd
	 * @param userType
	 * @return
	 */
	private boolean authStatus(String userName, String pwd, UserType userType ){
		Subject subject = SecurityUtils.getSubject();
		//设置用户的类型
		CustTokenManager.setAttr(Cons.USER_TYPE, userType);
		//对密码进行解密操作
		String password = PwdUtil.encrypt(pwd).toString();
		//创建验证需要的token
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		//设置“记住我”
		token.setRememberMe(true);
		boolean success = true;
		try {
			subject.login(token);
		} catch (AccountException e) {
			logger.debug("用户名或密码不正确");
			success = !success;
			e.printStackTrace();
		}catch (CredentialsException e) {
			logger.debug("用户名或密码不正确");
			success = !success;
			e.printStackTrace();
		}catch (AuthenticationException e) {
			logger.debug("身份验证异常....");
			success = !success;
			e.printStackTrace();
		}
		if(!success){
			token.clear();
		}
		return success;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
//		String msg = CustTokenManager.getString("sirding");
//		logger.debug("从shiro的sesion中取值：" + msg);
//		CustTokenManager.getSubject().logout();
		return "redirect:/auth/login.jsp";
	}
	
	@RequestMapping("authFail")
	public String authFail(){
		return "redirect:/auth/unauthorized.jsp";
	}
}
