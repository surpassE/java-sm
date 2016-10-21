package com.sirding.core.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustUsernamePasswordToken extends UsernamePasswordToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CustUsernamePasswordToken(){}
	
	public CustUsernamePasswordToken(final String username, final String password) {
        super(username, password != null ? password.toCharArray() : null, false, null);
    }
	
	public CustUsernamePasswordToken(final String username, final String password, final String userType) {
        super(username, password != null ? password.toCharArray() : null, false, null);
        this.userType = userType;
	}
	
	private String userType = "1";
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
