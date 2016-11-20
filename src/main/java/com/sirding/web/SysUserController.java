package com.sirding.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
public class SysUserController {
	
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	private AppSysUserService appSysUserService;

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
		List<AppSysUser> list = this.appSysUserService.findList(null);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Map<String, Object> find(Integer draw, Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", draw);
		map.put("recordsTotal", 45);
		map.put("recordsFiltered", 45);
		List<AppSysUser> list = this.appSysUserService.findList(null);
		map.put("data", list);
		return map;
	}
}
