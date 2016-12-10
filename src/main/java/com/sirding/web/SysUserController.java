package com.sirding.web;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sirding.base.BaseController;
import com.sirding.domain.dtpage.Page;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.service.AppSysUserService;

/**
 * @Described	: 系统管理员信息管理
 * @project		: com.sirding.web.SysUserController
 * @author 		: zc.ding
 * @date 		: 2016年11月20日
 */
@Controller
@RequestMapping("/sysUser/")
public class SysUserController extends BaseController{
	
	private final Logger logger = Logger.getLogger(getClass());
	public SysUserController(){
		super.prefix = "uiAdmin/user/";
		super.prefixRedirect = "redirect:uiAdmin/user/";
	}
	
	@Autowired
	private AppSysUserService appSysUserService;

	@RequestMapping("toSysUser")
	public String toSysUser(){
		logger.debug("系统用户管理页面");
		return getPrefix("sysUserList");
	}

	@RequestMapping("addSysUser")
	public ModelAndView addSysUser(AppSysUser obj){
		ModelAndView mav = new ModelAndView(super.getPrefix("sysUserList"));
		this.appSysUserService.add(obj);
		return mav;
	}

	@RequestMapping("delSysUser")
	public ModelAndView delSysUser(AppSysUser obj){
		ModelAndView mav = new ModelAndView(super.getPrefix("sysUserList"));
		this.appSysUserService.del(obj.getId());
		return mav;
	}

	@RequestMapping(value="sysUserList")
	@ResponseBody
	public List<AppSysUser> sysUserList(AppSysUser obj){
		List<AppSysUser> list = this.appSysUserService.findList(null);
		return list;
	}

//	@RequestMapping(value = "findSysUser", method = RequestMethod.POST)
//	@ResponseBody
//	public AppSysUser findSysUser(String loginName){
//		AppSysUser record = new AppSysUser();
//		record.setLoginName(loginName);
//		List<AppSysUser> list = this.appSysUserService.findList(null);
//		if(list != null && list.size() > 0){
//			return list.get(0);
//		}
//		return null;
//	}
	
	@RequestMapping(value = "findUser", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findUser(Page page, AppSysUser obj){
		List<AppSysUser> list = this.appSysUserService.findSysUser(page, obj);
		return super.getPageMap(page, list);
	}
}
