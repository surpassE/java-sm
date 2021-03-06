package com.sirding.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sirding.base.BaseController;
import com.sirding.commons.Cons;
import com.sirding.commons.Cons.UserType;
import com.sirding.core.shiro.CustTokenManager;
import com.sirding.core.utils.CookieUtil;
import com.sirding.core.utils.HttpSessionUtil;
import com.sirding.core.utils.LoggerUtils;
import com.sirding.core.utils.TokenUtil;
import com.sirding.core.utils.secure.PwdUtil;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.service.AppSysUserService;

@Controller
@RequestMapping("/")
public class AuthController extends BaseController{
	
	Logger logger = Logger.getLogger(AuthController.class);
	
	@Autowired
	private AppSysUserService appSysUserService;
	
	@RequestMapping("/")
	public String index(){
//		登录之前先过的access_token
//		Map<String, Object>	map = new HashMap<String, Object>();
//		map.put("grant_type", "password");
//		map.put("client_id", "mobile-client");
//		map.put("client_secret", "mobile");
//		map.put("scope", "read write");
//		map.put("username", "admin");
//		map.put("password", "sirding");
//		//获得access_token
//		String msg = HttpClientUtil.reqAndResMsg("http://localhost:8080/oauth/token", map, "1");
//		logger.info(msg);
		return "redirect:login.jsp";
		
	}
	
	/**
	 * 进入管理员登录页面
	 * @return
	 * @author zc.ding
	 * @date 2016年11月12日
	 */
	@RequestMapping("sysLogin")
	public String sysLogin(){
		return "sysLogin";
	}
	
	@RequestMapping("toHome")
	public String toHome(HttpServletRequest request){
		logger.debug("接收重定向前的属性:" + request.getParameter("test"));
//		return "home";
		return "adminIndex";
	}
	
	@RequestMapping("toAdminIndex")
	public String toAdminIndex(){
		return "adminIndex";
	}

	/**
	 * 验证应用用户信息
	 * @date 2016年10月21日
	 * @author zc.ding
	 * @param userName
	 * @param pwd
	 * @return
	 */
	@RequestMapping("login")
	public String login(String userName, String pwd){
		if(!this.authStatus(userName, pwd, UserType.APP_USER)){
			return "/home";
		}
		return "home";
	}
	
	/**
	 * 系统管理员验证验证操作
	 * @date 2016年10月21日
	 * @author zc.ding
	 * @param userName
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "adminLogin")
	public ModelAndView adminLogin(String userName, String pwd){
//		if(!this.authStatus(userName, pwd, UserType.APP_SYS_USER)){
//			return "redirect:/auth/login.jsp";
//		}
		ModelAndView view = new ModelAndView("redirect:toAdminIndex");
		LoggerUtils.debugForTest(getClass(), "执行controller中的方法...");
//		view.addObject("test", "test");
		AppSysUser sysUser = new AppSysUser();
		sysUser.setLoginName(userName);
		AppSysUser currUser = this.appSysUserService.findList(sysUser).get(0);
		//将用户信息保存到session中
		HttpSessionUtil.saveAppSysUser(currUser);
		
		//生成用于存储cookie的唯一标示
		String token = TokenUtil.getToken();
		Cookie cookie = CookieUtil.getCookie(Cons.COOKIE_USER, token);
		HttpSessionUtil.getResponse().addCookie(cookie);
		//将用户信息保存到redis中
		HttpSessionUtil.saveAppSysUserToRedis(currUser, token);
		return view;
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
	
	@RequestMapping(value="logout")
	public String logout(HttpSession session){
//		String msg = CustTokenManager.getString("sirding");
//		logger.debug("从shiro的sesion中取值：" + msg);
//		CustTokenManager.getSubject().logout();
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("authFail")
	public String authFail(){
		return "redirect:/auth/unauthorized.jsp";
	}
	
	@RequestMapping("oauth")
	public String oauth(){
		return "oauth";
	}
}
