package com.sirding.core.security.shiro.realm;

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

import com.sirding.core.utils.secure.PwdUtil;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.service.AppSysUserService;

/**
 * 
 * @author zc.ding
 * @date 2016年10月20日
 */
public class AppSysUserRealm extends AuthorizingRealm {
	
	Logger logger = Logger.getLogger(AppSysUserRealm.class);
	
	@Autowired
	private AppSysUserService appSysUserService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
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
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.debug(token);
		String userName = (String)token.getPrincipal();
		logger.debug("userName:" + userName);
		AppSysUser appSysUser = new AppSysUser();
		appSysUser.setId(1);
		appSysUser.setLoginName(userName);	
		List<AppSysUser> userList = this.appSysUserService.findList(appSysUser);
		if(userList == null || userList.size() == 0){
			throw new AccountException();
		}
		String password = PwdUtil.encrypt(userName).toString();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, password, getName());
		return info;
	}

}
