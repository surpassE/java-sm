package com.sirding.core.shiro.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import com.sirding.commons.Cons.UserType;
import com.sirding.core.shiro.CustTokenManager;
import com.sirding.mybatis.model.AppPerm;
import com.sirding.mybatis.model.AppRole;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUser;
import com.sirding.service.AppPermService;
import com.sirding.service.AppRoleService;
import com.sirding.service.AppSysUserService;
import com.sirding.service.AppUserService;

public class CustUserRealm extends AuthorizingRealm{

	@Autowired
	private AppSysUserService appSysUserService;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AppPermService appPermService;
	@Autowired
	private AppRoleService appRoleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//通过userName查询对应的role、permission
	    List<String> list = new ArrayList<String>(); 
	    List<AppPerm> permList = null;
	    Set<String> set = new HashSet<String>();
	    List<AppRole> roleList = null;
	    
	    UserType userType = CustTokenManager.getUserType();
		switch(userType){
		case APP_USER:
			permList = this.appPermService.findPermByUserName(userName);
			roleList = this.appRoleService.findRoleByUserName(userName);
			break;
		case APP_SYS_USER:
			permList = this.appPermService.findPermBySysUserName(userName);
			roleList = this.appRoleService.findRoleBySysUserName(userName);
			break;
		}
		//添加用户权限
		if(permList != null){
			for(AppPerm ap : permList){
				list.add(ap.getWildcard());
			}
		}
		info.addStringPermissions(list);
		//添加用户角色
		if(roleList != null){
			for(AppRole ar : roleList){
				set.add(ar.getName());
			}
		}
		info.addRoles(set);
	    return info;

	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		String pwd = null;
		UserType userType = CustTokenManager.getUserType();
		SimpleAuthenticationInfo info = null;
		switch(userType){
		case APP_USER:
			AppUser appUser = new AppUser();
			appUser.setLoginName(userName);
			List<AppUser> list = this.appUserService.findList(appUser);
			if(list == null || list.size() == 0){
				throw new AccountException();
			}
			pwd = list.get(0).getLoginPwd();
			break;
		case APP_SYS_USER:
			AppSysUser appSysUser = new AppSysUser();
			appSysUser.setLoginName(userName);
			List<AppSysUser> sysList = this.appSysUserService.findList(appSysUser);
			if(sysList == null || sysList.size() == 0){
				throw new AccountException();
			}
			pwd = sysList.get(0).getLoginPwd();
			break;
		}
//		String password = PwdUtil.encrypt(userName).toString();
		info = new SimpleAuthenticationInfo(userName, pwd, getName());
		return info;
	}

}
