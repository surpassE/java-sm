package com.sirding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sirding.base.BaseController;
import com.sirding.service.AppMenuService;
import com.sirding.service.AppPermService;
import com.sirding.service.AppRoleService;
import com.sirding.service.AppSysUserService;

@Controller
@RequestMapping("/sec")
public class SecController extends BaseController{

	@Autowired
	private AppSysUserService appSysUserService;
	@Autowired
	private AppRoleService appRoleService;
	@Autowired
	private AppMenuService appMenuService;
	@Autowired
	private AppPermService appPermService;
	
	
	@RequestMapping(value = "toSysUser", method = RequestMethod.POST)
	public String toSysUser(){
		return "sec/sysUser";
	}
	
	@RequestMapping(value = "toRole", method = RequestMethod.POST)
	public String toRole(){
		
		return "sec/role";
	}
	
	@RequestMapping(value = "toMenu", method = RequestMethod.POST)
	public String toMenu(){
		return "sec/menu";
	}
	
	@RequestMapping("toPerm")
	public String toPerm(){
		return "sec/perm";
	}
	
}
