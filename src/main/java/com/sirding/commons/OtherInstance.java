package com.sirding.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sirding.core.utils.ACUtils;

@Component("otherInstance")
public class OtherInstance {
	private static OtherInstance otherInstance = null;

	public static OtherInstance newInstance(){
		if(otherInstance == null){
			otherInstance = ACUtils.getBean("otherInstance", OtherInstance.class);
		}
		return otherInstance;
	}
	
	@Value("#{other.redis_ip}")
	public String REDIS_IP;
	@Value("#{other.redis_port}")
	public String REDIS_PORT;
}
