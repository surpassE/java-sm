package com.sirding.core.security.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.Realm;

public class MyShiroRealm implements Realm{

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, userName, getName());
		return info;
	}

	@Override
	public String getName() {
		return "IMSR";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return true;
	}
}
