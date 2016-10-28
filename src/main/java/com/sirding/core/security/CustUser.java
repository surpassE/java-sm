package com.sirding.core.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 自定义Spring security User继承类，扩展盐源自定义算法
 * @author zc.ding
 * @date 2016年10月28日
 */
public class CustUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CustUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this(username, password, true, true, true, true, authorities);
	}

	public CustUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

}
