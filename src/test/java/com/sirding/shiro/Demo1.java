package com.sirding.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Test;

public class Demo1 {

	@Test
	public void test(){
		try {
			Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
			SecurityManager securityManager = factory.getInstance();
			DefaultWebSessionManager sm = new org.apache.shiro.web.session.mgt.DefaultWebSessionManager();
			sm.setGlobalSessionTimeout(100);
			AuthenticationInfo authenticationInfo = securityManager.authenticate(null);
			authenticationInfo.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
