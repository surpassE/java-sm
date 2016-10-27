package com.sirding.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sirding.commons.Cons.UserType;
import com.sirding.core.utils.LoggerUtils;
import com.sirding.mybatis.model.AppSysUser;
import com.sirding.mybatis.model.AppUser;
import com.sirding.service.AppSysUserService;
import com.sirding.service.AppUserService;
import com.sirding.service.CustUserDetailService;

@Service("custUserDetailService")
public class CustUserDetailServiceImpl implements CustUserDetailService {
	
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private AppSysUserService appSysUserService;
	
	@Override
	public UserDetails loadUserByUsername(String username)	throws UsernameNotFoundException {
		LoggerUtils.debugForTest(getClass(), "到底有没有执行这里......");
		String[] arr = username.split("-");
		String pos = arr[0];
		username = arr[1];
		String password = "";
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(UserType.APP_USER.toString().equalsIgnoreCase(pos)){
			AppUser appUser = new AppUser();
			appUser.setLoginName(username);
			List<AppUser> list = this.appUserService.findList(appUser);
			if(list != null && list.size() > 0){
				password = list.get(0).getLoginPwd();
			}
		}else if(UserType.APP_SYS_USER.toString().equalsIgnoreCase(pos)){
			AppSysUser appSysUser = new AppSysUser();
			appSysUser.setLoginName(username);
			List<AppSysUser> list = this.appSysUserService.findList(appSysUser);
			if(list != null && list.size() > 0){
				password = list.get(0).getLoginPwd();
			}
		}
		return new User(username, password, authorities);
	}

}
