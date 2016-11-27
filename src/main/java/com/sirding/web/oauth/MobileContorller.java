package com.sirding.web.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sirding.base.BaseController;

/**
 * @Described	: 用于测试Mobile的access_token 
 * @project		: com.sirding.web.oauth.MobileContorller
 * @author 		: zc.ding
 * @date 		: 2016年11月27日
 */
@Controller
@RequestMapping("/mobile/")
public class MobileContorller extends BaseController{

	@RequestMapping("view")
	public String view(){
		return "oauth/mobile";
	}
}
