package com.sirding.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUser;
import com.sirding.service.AppSysUserService;
import com.sirding.service.AppUserService;

/**
 * 
 * @author zc.ding
 * @date 2016年9月14日
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AppSysUserService appSysUserService;
	
	/**
	 * 
	 * @date 2016年9月14日
	 * @author zc.ding
	 * @return
	 */
	@RequestMapping("userList")
	public String userList(AppUser obj){
		logger.debug("进入findUser接口...");
		return "user/userList";
	}
	
	@RequestMapping("/toHome")
	public String toHome(){
		logger.debug("进入主页");
		return "shiro/home";
	}
	
	@RequestMapping("addUser")
	public String addUser(AppUser obj){
		this.appUserService.add(obj);
		return "redirect:uert/toUser.htm";
	}
	
	@RequestMapping("toSysUser")
	public String toSysUser(){
		return "user/sysuser";
	}
	
	@RequestMapping("sysUserList")
	@ResponseBody
	public List<AppSysUser> sysUserList(AppSysUser obj){
		return this.appSysUserService.findList(null);
	}
	
	@RequestMapping("addSysUser")
	public ModelAndView addSysUser(AppSysUser obj){
		ModelAndView mav = new ModelAndView("user/sysuser");
		this.appSysUserService.add(obj);
		return mav;
	}
	
	@RequestMapping("delSysUser")
	public ModelAndView delSysUser(AppSysUser obj){
		ModelAndView mav = new ModelAndView("user/sysuser");
		this.appSysUserService.del(obj.getId());
		return mav;
	}
	
	@RequestMapping(value = "findSysUser", method = RequestMethod.POST)
	@ResponseBody
	public AppSysUser findSysUser(String loginName){
		AppSysUser record = new AppSysUser();
		record.setLoginName(loginName);
		List<AppSysUser> list = this.appSysUserService.findList(record);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
