package com.sirding.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sirding.service.UserService;

/**
 * 
 * @author zc.ding
 * @date 2016年9月14日
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @date 2016年9月14日
	 * @author zc.ding
	 * @return
	 */
	@RequestMapping("/findUser")
	public String findUser(){
		logger.debug("进入findUser接口...");
		this.userService.findUser(0);
		return "user/userList";
	}
	
	@RequestMapping("/toHome")
	public String toHome(){
		logger.debug("进入主页");
		return "shiro/home";
	}
	

}
