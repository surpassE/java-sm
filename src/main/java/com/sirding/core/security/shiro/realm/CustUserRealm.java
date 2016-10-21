package com.sirding.core.security.shiro.realm;

import java.util.List;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.sirding.commons.Cons.UserType;
import com.sirding.core.security.shiro.CustTokenManager;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUser;
import com.sirding.service.AppSysUserService;
import com.sirding.service.AppUserService;

public class CustUserRealm extends AuthorizingRealm{

	@Autowired
	private AppSysUserService appSysUserService;
	@Autowired
	private AppUserService appUserService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String)token.getPrincipal();
		String pwd = null;
		UserType userType = CustTokenManager.getUserType();
		SimpleAuthenticationInfo info = null;
		switch(userType){
		case APPUSER:
			AppUser appUser = new AppUser();
			appUser.setLoginName(userName);
			List<AppUser> list = this.appUserService.findList(appUser);
			if(list == null || list.size() == 0){
				throw new AccountException();
			}
			pwd = list.get(0).getLoginPwd();
			break;
		case APPSYSUSER:
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
