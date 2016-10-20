package com.sirding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sirding.base.BaseController;
import com.sirding.base.Result;
import com.sirding.mybatis.model.AppPerm;
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
	
	
	@RequestMapping(value = "toRole", method = RequestMethod.POST)
	public String toRole(){
		
		return "sec/role";
	}
	
	@RequestMapping(value = "toMenu", method = RequestMethod.POST)
	public String toMenu(){
		return "sec/menu";
	}
	
	@RequestMapping("toPerm")
	public ModelAndView toPerm(){
		ModelAndView mav = new ModelAndView("sec/perm");
		mav.addObject("list", this.appPermService.findList(null));
		return mav;
	}
	
	@RequestMapping("addPerm")
	@ResponseBody
	public Result addPerm(AppPerm appPerm){
		this.appPermService.add(appPerm);
		return new Result(appPerm);
	}
	
	@RequestMapping("delPerm")
	public String delPerm(AppPerm perm){
		this.appMenuService.del(perm.getId());
		return "redirect:sec/toPerm";
	}
	
	
}
