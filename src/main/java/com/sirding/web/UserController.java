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
import com.sirding.mybatis.model.OauthClientDetails;
import com.sirding.service.AppSysUserService;
import com.sirding.service.AppUserService;
import com.sirding.service.OauthClientDetailsService;


/**
 * @Described	: 账户管理
 * @project		: com.sirding.web.UserController
 * @author 		: zc.ding
 * @date 		: 2016年11月15日
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AppSysUserService appSysUserService;
	@Autowired
	private OauthClientDetailsService oauthClientDetailsService;
	
	//***********应用用户管理******************* 华丽分割线 *******************************
	/**
	 * 
	 * @date 2016年9月14日
	 * @author zc.ding
	 * @return
	 */
	@RequestMapping("toUser")
	public String toUser(AppUser obj){
		logger.debug("进入应用管理用户界面");
		return "user/userList";
	}
	
	/**
	 * 添加应用用户信息
	 * @param obj
	 * @return
	 * @author zc.ding
	 * @date 2016年11月13日
	 */
	@RequestMapping("addUser")
	public String addUser(AppUser obj){
		this.appUserService.add(obj);
		return "redirect:uert/toUser.htm";
	}
	
	public ModelAndView delUser(AppUser obj){
		ModelAndView mav = new ModelAndView("user/userList");
		return mav;
	}
	
	/**
	 * 查询应用用户信息
	 * @param obj
	 * @return
	 * @author zc.ding
	 * @date 2016年11月13日
	 */
	@RequestMapping("userList")
	@ResponseBody
	public List<AppUser> userList(AppUser obj){
		return this.appUserService.findList(null);
	}
	
	//**************系统用户管理************* 华丽分割线 *************************
	
	@RequestMapping("toSysUser")
	public String toSysUser(){
		logger.debug("系统用户管理页面");
		return "user/sysUserList";
	}
	
	@RequestMapping("addSysUser")
	public ModelAndView addSysUser(AppSysUser obj){
		ModelAndView mav = new ModelAndView("user/sysUserList");
		this.appSysUserService.add(obj);
		return mav;
	}
	
	@RequestMapping("delSysUser")
	public ModelAndView delSysUser(AppSysUser obj){
		ModelAndView mav = new ModelAndView("user/sysUserList");
		this.appSysUserService.del(obj.getId());
		return mav;
	}
	
	@RequestMapping(value="sysUserList")
	@ResponseBody
	public List<AppSysUser> sysUserList(AppSysUser obj){
		List<AppSysUser> list = this.appSysUserService.findList(null);
		return list;
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
	
	
	//********************************* oauth 管理 *********************************
	
	/**
	 * 
	 * @Described	: 进入clent detail信息配置页面
	 * @author		: zc.ding
	 * @date 		: 2016年11月15日
	 * @return		: ModelAndView
	 */
	@RequestMapping(value = "toClientDetail")
	public ModelAndView toClientDtail(){
		ModelAndView view = new ModelAndView("oauth/clientDetails");
//		List<CustClientDetails> list = this.custClientDetailsService.findClientDetails();
		List<OauthClientDetails> list = this.oauthClientDetailsService.findList(null);
		view.addObject("list", list);
		return view;
	}
	
}
