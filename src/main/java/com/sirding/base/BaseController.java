package com.sirding.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.sirding.commons.Cons;
import com.sirding.core.utils.TokenUtil;
import com.sirding.domain.dtpage.Page;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUser;

/**
 * @Described	: controller基类
 * @project		: com.sirding.base.BaseController
 * @author 		: zc.ding
 * @date 		: 2016年11月18日
 */
public abstract class BaseController {
	private final Logger logger = Logger.getLogger(getClass());
	/**
	 * 视图前缀
	 */
	protected String prefix = "";
	/**
	 * 重定向视图前缀
	 */
	protected String prefixRedirect;
	
	/**
	 * @Described			: 获得视图的前缀名称
	 * @author				: zc.ding
	 * @date 				: 2016年12月10日
	 * @return				: String
	 * @param name
	 * @return
	 */
	protected String getPrefix(String name){
		return this.prefix + name;
	}
	
	/**
	 * @Described			: 获得重定向的视图前缀
	 * @author				: zc.ding
	 * @date 				: 2016年12月10日
	 * @return				: String
	 * @param name
	 * @return
	 */
	protected String getPrefixRedirect(String name){
		return this.prefixRedirect + name;
	}

	/**
	 * @Described	: 获得含有token的视图对象
	 * @author		: zc.ding
	 * @date 		: 2016年11月16日
	 * @return		: ModelAndView	含有token的请求
	 * @param uri
	 */
	protected ModelAndView getTokenView(String uri){
		return this.getTokenView(this.getSession(), uri);
	}
	
	/**
	 * @Described	: 获得含有token的视图对象
	 * @author		: zc.ding
	 * @date 		: 2016年11月16日
	 * @return		: ModelAndView	含有token的请求
	 * @param session
	 * @param uri
	 */
	protected ModelAndView getTokenView(HttpSession session, String uri){
		ModelAndView view = new ModelAndView(uri);
		String token = TokenUtil.getToken();
		view.addObject(Cons.RESUBMIT_TOKEN, token);
		view.addObject(Cons.CSRF_TOKEN, token);
		session.setAttribute(Cons.RESUBMIT_TOKEN, token);
		session.setAttribute(Cons.CSRF_TOKEN, token);
		logger.info("生成的token：" + token);
		return view;
	}
	
	/**
	 * @Described	: 验证请求是否为重复提交的操作
	 * @author		: zc.ding
	 * @date 		: 2016年11月17日
	 * @return		: boolean	true:合法的请求	false:重复提交的请求
	 * @param request
	 */
	protected boolean validateToken(HttpServletRequest request){
		boolean flag = false;
		HttpSession session = this.getSession();
		String token = request.getParameter(Cons.RESUBMIT_TOKEN);
		String cmpToken = (String)session.getAttribute(Cons.RESUBMIT_TOKEN);
		if(token != null && token.equals(cmpToken)){
			session.removeAttribute(Cons.RESUBMIT_TOKEN);
			flag = true;
		}
		if(logger.isDebugEnabled()){
			logger.debug("token验证结果：" + flag + "session中存储的token：" + cmpToken + ", 需要验证的token:" + token);
			logger.debug("session中存储的token：" + cmpToken);
			logger.debug("需要验证的token:" + token);
		}
		return flag;
	}
	
	/**
	 * @Described	: 重复提交的请求返回原地址
	 * @author		: zc.ding
	 * @date 		: 2016年11月17日
	 * @return		: ModelAndView
	 * @param uri	: 目标地址uri
	 * @param name	: 
	 * @param object: 用于在页面中展示的数据信息
	 */
	protected ModelAndView backView(String uri, String name, Object object){
		ModelAndView view = new ModelAndView(uri);
		view.addObject(name, object);
		view.addObject(Cons.ERROR_MSG, "此请求已过期");
		return view;
	}
	
	/**
	 * @Described	: 重复提交的请求返回原地址
	 * @author		: zc.ding
	 * @date 		: 2016年11月17日
	 * @return		: ModelAndView
	 * @param uri	：目标地址
	 * @param map	: 需要带回元页面中的数据信息
	 */
	protected ModelAndView backView(String uri, Map<String, Object> map){
		ModelAndView view = new ModelAndView(uri);
		if(map != null){
			for(String key : map.keySet()){
				view.addObject(key, map.get(key));
			}
		}
		view.addObject(Cons.ERROR_MSG, "此请求已过期");
		return view;
	}
	
	/**
	 * @Described	: 获得请求的session
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: HttpSession
	 * @return
	 */
	protected HttpSession getSession(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}
	
	/**
	 * @Described	: 从session中获得AppUser对象
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: AppUser
	 * @return
	 */
	protected AppUser getAppUser(){
		return (AppUser)this.getSession().getAttribute(Cons.UserType.APP_USER.name());
	}
	
	/**
	 * 
	 * @Described	: 从session获得AppSysUser对象
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: AppSysUser
	 * @return
	 */
	protected AppSysUser getAppSysUser(){
		return (AppSysUser)this.getSession().getAttribute(Cons.UserType.APP_SYS_USER.name());
	}
	
	/**
	 * @Described	: 保存AppUser信息到seesion中
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: void
	 * @param appUser
	 */
	protected void saveAppUser(AppUser appUser){
		this.getSession().setAttribute(Cons.UserType.APP_USER.name(), appUser);
	}

	/**
	 * @Described	: 存AppSysUser信息到seesion中
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: void
	 * @param appSysUser
	 */
	protected void saveAppSysUser(AppSysUser appSysUser){
		this.getSession().setAttribute(Cons.UserType.APP_SYS_USER.name(), appSysUser);
	}
	
	/**
	 * @Described	: 异步验证数据唯一值得响应结果
	 * @author		: zc.ding
	 * @date 		: 2016年11月19日
	 * @return		: Map<String,Boolean>
	 * @param flag	: true ：有效(验证的id不存在)  false ：无效
	 * @return
	 */
	protected Map<String, Boolean> getValidateMap(Boolean flag){
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("valid", flag);
		return map;
	}
	
	/**
	 * @Described	: 获得分页的返回结构信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月23日
	 * @return		: Map<String,Object>
	 * @param page
	 * @param total
	 * @param list
	 * @return
	 */
	protected Map<String, Object> getPageMap(Page page, long total, Object list){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("draw", page.getDraw());
		map.put("recordsTotal", total);
		map.put("recordsFiltered", total);
		map.put("data", list);
		return map;
	}
	
	/**
	 * @Described	: 获得分页的返回结构信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月23日
	 * @return		: Map<String,Object>
	 * @param page
	 * @param list
	 * @return
	 */
	protected Map<String, Object> getPageMap(Page page, Object list){
		Map<String, Object> map = new HashMap<String, Object>();
		long total = page.getTotal();
		map.put("draw", page.getDraw());
		map.put("recordsTotal", total);
		map.put("recordsFiltered", total);
		map.put("data", list);
		return map;
	}
}
