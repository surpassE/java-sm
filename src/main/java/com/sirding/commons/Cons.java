package com.sirding.commons;

public class Cons {
	
	//用户 类型
	public static final String USER_TYPE = "USER_TYPE";
	//防重复提交的token
	public static final String RESUBMIT_TOKEN = "resubmitToken";
	public static final String CSRF_TOKEN = "csrfToken";
	
	public static final String COOKIE_USER = "COOKIE_USER";
	
	//错误提示信息
	public static final String ERROR_MSG = "errMsg";

	/**
	 * 标志用户的类型
	 * @author zc.ding
	 * @date 2016年10月21日
	 */
	public enum UserType {
		APP_SYS_USER(1),	//系统管理员
		APP_USER(2);		//应用用户
		private final int value;

		UserType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
