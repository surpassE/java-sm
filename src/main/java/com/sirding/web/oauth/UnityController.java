package com.sirding.web.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sirding.base.BaseController;
/**
 * @Described	: 测试untiy的access_token
 * @project		: com.sirding.web.oauth.UnityController
 * @author 		: zc.ding
 * @date 		: 2016年11月27日
 */
@Controller
@RequestMapping("/unity/")
public class UnityController extends BaseController{

	@RequestMapping("view")
	public String view(){
		return "oauth/unity";
	}
}
