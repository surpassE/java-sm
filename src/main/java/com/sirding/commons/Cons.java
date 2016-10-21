package com.sirding.commons;

public class Cons {
	
	//用户 类型
	public static final String USER_TYPE = "USER_TYPE";

	/**
	 * 标志用户的类型
	 * @author zc.ding
	 * @date 2016年10月21日
	 */
	public enum UserType {
		APPSYSUSER(1),	//系统管理员
		APPUSER(2);		//应用用户
		private final int value;

		UserType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
