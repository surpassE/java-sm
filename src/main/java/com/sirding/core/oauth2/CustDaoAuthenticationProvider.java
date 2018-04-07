package com.sirding.core.oauth2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class CustDaoAuthenticationProvider extends DaoAuthenticationProvider {

	@SuppressWarnings({ "unused", "unchecked" })
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		if(authentication.getDetails() != null && authentication.getDetails() instanceof HashMap<?, ?>) {
			Map<String, String> map = (Map<String, String>) authentication.getDetails();
			//获得密码长度，对密码进行RSA解密
			Integer pwdLength = Integer.parseInt(Optional.ofNullable(map.get("pwdLength")).orElse("0"));
			Object pwd = authentication.getCredentials();
			authentication = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), pwd);
					((AbstractAuthenticationToken) authentication).setDetails(map);
		}
		super.additionalAuthenticationChecks(userDetails, authentication);
	}
}
