package com.sirding.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sirding.base.BaseController;
import com.sirding.domain.dtpage.Page;
import com.sirding.mybatis.model.AppUser;
import com.sirding.service.AppUserService;


/**
 * @Described	: 应用用户信息管理
 * @project		: com.sirding.web.UserController
 * @author 		: zc.ding
 * @date 		: 2016年11月15日
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController {
	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private AppUserService appUserService;
	
	/**
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

	/**
	 * @Described	: 删除应用用户信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月13日
	 * @return		: ModelAndView
	 * @param obj
	 * @return
	 */
	@RequestMapping("delUser")
	public ModelAndView delUser(AppUser obj){
		ModelAndView mav = new ModelAndView("user/userList");
		this.appUserService.del(obj.getId());
		return mav;
	}
	
	/**
	 * @Described	: 进入用户更新页面
	 * @author		: zc.ding
	 * @date 		: 2016年11月13日
	 * @return		: ModelAndView
	 * @param obj
	 * @return
	 */
	public ModelAndView toUpdateUser(AppUser obj){
		ModelAndView view = super.getTokenView("user/user");
		AppUser user = this.appUserService.findById(obj.getId());
		view.addObject("user", user);
		return view;
	}
	
	/**
	 * @Described	: 更新用户信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月13日
	 * @return		: ModelAndView
	 * @param request
	 * @param obj
	 * @return
	 */
	public ModelAndView updateUser(HttpServletRequest request, AppUser obj){
		ModelAndView view = new ModelAndView("redirect:/user/toUser");
		if(super.validateToken(request)){
			return super.backView("redirect:/user/toUpdateUser", "user", obj);
		}
		return view;
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
	
	
	@RequestMapping("findUser")
	@ResponseBody
	public Map<String, Object> findUser(Page page, AppUser obj){
		List<AppUser> list = this.appUserService.findUser(page, obj);
		return super.getPageMap(page, list);
	}
}
