package com.sirding.base;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.sirding.commons.Cons;

public abstract class BaseController {
	private final Logger logger = Logger.getLogger(getClass());

	/**
	 * @Described	: 获得含有token的视图对象
	 * @author		: zc.ding
	 * @date 		: 2016年11月16日
	 * @return		: ModelAndView	含有token的请求
	 * @param request
	 * @param uri
	 */
	protected ModelAndView getTokenView(HttpServletRequest request, String uri){
		HttpSession session = request.getSession();
		ModelAndView view = new ModelAndView(uri);
		String token = UUID.randomUUID().toString();
		view.addObject("token", token);
		session.setAttribute(Cons.REPEAT_TOKEN, token);
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
		HttpSession session = request.getSession();
		String token = request.getParameter("token");
		String cmpToken = (String)session.getAttribute(Cons.REPEAT_TOKEN);
		logger.info("session中存储的token：" + cmpToken + ", 需要验证的token:" + token);
		if(token != null && token.equals(cmpToken)){
			session.removeAttribute(Cons.REPEAT_TOKEN);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @Described	: 重复提交的请求返回原地址
	 * @author		: zc.ding
	 * @date 		: 2016年11月17日
	 * @return		: ModelAndView
	 * @param uri
	 * @param key
	 * @param object
	 */
	protected ModelAndView backView(String uri, String key, Object object){
		ModelAndView view = new ModelAndView(uri);
		view.addObject(key, object);
		view.addObject(Cons.ERR_MSG, "此请求已过期");
		return view;
	}
	
	/**
	 * @Described	: 重复提交的请求返回原地址
	 * @author		: zc.ding
	 * @date 		: 2016年11月17日
	 * @return		: ModelAndView
	 * @param uri
	 * @param map
	 */
	protected ModelAndView backView(String uri, Map<String, Object> map){
		ModelAndView view = new ModelAndView(uri);
		if(map != null){
			for(String key : map.keySet()){
				view.addObject(key, map.get(key));
			}
		}
		view.addObject(Cons.ERR_MSG, "此请求已过期");
		return view;
	}
	
}
