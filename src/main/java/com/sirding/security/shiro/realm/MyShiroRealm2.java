package com.sirding.security.shiro.realm;

import java.security.Principal;

import org.apache.http.auth.Credentials;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyShiroRealm2 extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("执行.....1");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行.....2");
		Principal principal = (Principal)arg0.getPrincipal();
		String name = principal.getName();
		Credentials credentials = (Credentials)arg0.getCredentials();
		String password = credentials.getPassword();
		return null;
	}


}
