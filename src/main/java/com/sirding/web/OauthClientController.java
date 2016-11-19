package com.sirding.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sirding.base.BaseController;
import com.sirding.mybatis.model.OauthClientDetails;
import com.sirding.service.OauthClientDetailsService;

/**
 * @Described	: oauth_client_detail控制类
 * @project		: com.sirding.web.OauthClientController
 * @author 		: zc.ding
 * @date 		: 2016年11月19日
 */
@Controller
@RequestMapping("/oauthClient")
public class OauthClientController extends BaseController{

	@Autowired
	private OauthClientDetailsService oauthClientDetailsService;
	
	/**
	 * @Described	: 进入clent detail信息配置页面
	 * @author		: zc.ding
	 * @date 		: 2016年11月15日
	 * @return		: ModelAndView
	 */
	@RequestMapping(value = "toClientDetail")
	public ModelAndView toClientDtail(){
		ModelAndView view = new ModelAndView("oauth/clientDetails");
		List<OauthClientDetails> list = this.oauthClientDetailsService.findList(null);
		view.addObject("list", list);
		return view;
	}
	
	/**
	 * @Described	: 进入oauth client detail 注册页面
	 * @author		: zc.ding
	 * @date 		: 2016年11月16日
	 * @return		: ModelAndView
	 * @return
	 */
	@RequestMapping(value = "toRegistClient")
	public ModelAndView toRegistClient(HttpServletRequest request, HttpSession session){
		return super.getTokenView(session, "oauth/registClient");
	}
	
	/**
	 * @Described	: 注册client，添加oauth_client_details数据
	 * @author		: zc.ding
	 * @date 		: 2016年11月17日
	 * @return		: ModelAndView
	 * @param object
	 */
	@RequestMapping(value = "registClient", method = RequestMethod.POST)
	public ModelAndView registClient(HttpServletRequest request, OauthClientDetails object){
		ModelAndView view = new ModelAndView("redirect:toClientDetail");
		//验证token防止重复提交
		if(!super.validateToken(request)){
			return super.backView("oauth/registClient", "client", object);
		}
		this.oauthClientDetailsService.add(object);
		return view;
	}
	
	/**
	 * @Described	: 通过clientId删除指定的clientId配置信息
	 * @author		: zc.ding
	 * @date 		: 2016年11月19日
	 * @return		: ModelAndView
	 * @param clientId
	 * @return
	 */
	@RequestMapping("delClient/{clientId}")
	public ModelAndView delClient(@PathVariable("clientId") String clientId){
		ModelAndView view = new ModelAndView("redirect:/oauthClient/toClientDetail");
		this.oauthClientDetailsService.del(clientId);
		return view;
	}
	
	/**
	 * @Described	: 进入或的token的页面
	 * @author		: zc.ding
	 * @date 		: 2016年11月19日
	 * @return		: ModelAndView
	 * @param clientId
	 * @return
	 */
	@RequestMapping("toGetAccessToken")
	public ModelAndView toGetAccessToken(String clientId){
		ModelAndView view = new ModelAndView("oauth/getAccessToken");
		OauthClientDetails clientDetail = this.oauthClientDetailsService.findByClientId(clientId);
		view.addObject("clientDetail", clientDetail);
		return view;
	}
	
	/**
	 * @Described	: 校验要添加的clientId是否已经存在
	 * @author		: zc.ding
	 * @date 		: 2016年11月19日
	 * @return		: Map<String,Boolean>
	 * @param clientId
	 * @return
	 */
	@RequestMapping("checkClientId")
	@ResponseBody
	public Map<String, Boolean> checkClientId(String clientId){
		OauthClientDetails client = this.oauthClientDetailsService.findByClientId(clientId);
		System.out.println("验证的clientId：" + clientId);
		if(client != null && client.getClientId().length() > 0){
			return super.getValidateMap(false);
		}
		return super.getValidateMap(true);
	}
}
