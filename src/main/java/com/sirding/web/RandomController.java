package com.sirding.web;

import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sirding.domain.SimpleJson;
/**
 * @Described			: 
 * @project				: com.sirding.web.RandomController
 * @author 				: zc.ding
 * @date 				: 2016年12月3日
 */
@Controller
public class RandomController {
	private final Logger logger = Logger.getLogger(getClass());

	@RequestMapping("toRandom")
	public String toRandom(){
		return "random";
	}
	
	@MessageMapping("/random")
	@SendToUser(value = "/topic/getRandom")
	public SimpleJson getRandom(SimpleJson simpleJson){
		logger.debug("接收到客户端的数据信息：" + simpleJson.getName());
		
		SimpleJson json = new SimpleJson("zc.ding", Math.random());
		return json;
	}
}
