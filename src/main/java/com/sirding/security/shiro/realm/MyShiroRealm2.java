package com.sirding.security.shiro.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirding.mybatis.model.UserInfo;
import com.sirding.service.UserService;

public class MyShiroRealm2 extends AuthorizingRealm{

	Logger logger = Logger.getLogger(MyShiroRealm2.class);
	
	@Autowired
	private UserService userService;
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		String userName = (String) arg0.getPrimaryPrincipal();
		logger.debug("userName:" + userName);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		//通过userName查询对应的role、permission
	    List<String> permission = new ArrayList<String>();//模拟从数据库中取得的权限信息
	    permission.add("user:query");
	    permission.add("user:update");
	    permission.add("user:commit");
	    info.addStringPermissions(permission);
	    
	    //查询用户对应的角色
	    Set<String> roleSet = new HashSet<String>();
	    roleSet.add("admin");
	    info.setRoles(roleSet);
	    
	    return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.debug(token);
		String userName = (String)token.getPrincipal();
		logger.debug("userName:" + userName);
		UserInfo user = new UserInfo();
		user.setName(userName);
		List<UserInfo> list = this.userService.findUsers(user);
		if(list == null || list.size() == 0){
			throw new AccountException();
		}
		logger.debug("realm name:" + getName());
		char[] password = (char[])token.getCredentials();
		logger.debug("客户输入的密码：password:" + new String(password));
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, user.getName(), getName());
		return info;
	}


	void showFillters(){
//		anon org.apache.shiro.web.filter.authc.AnonymousFilter

//		authc org.apache.shiro.web.filter.authc.FormAuthenticationFilter

//		authcBasic org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter

//		logout org.apache.shiro.web.filter.authc.LogoutFilter

//		noSessionCreation org.apache.shiro.web.filter.session.NoSessionCreationFilter

//		perms org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter

//		port org.apache.shiro.web.filter.authz.PortFilter

//		rest org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter

//		roles org.apache.shiro.web.filter.authz.RolesAuthorizationFilter

//		ssl org.apache.shiro.web.filter.authz.SslFilter

//		user org.apache.shiro.web.filter.authc.UserFilter
	}
}
