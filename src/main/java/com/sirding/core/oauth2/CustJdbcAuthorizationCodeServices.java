package com.sirding.core.oauth2;

import javax.sql.DataSource;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

/**
 * 扩展{@link org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices}
 * 用于自定义生成的code的长度
 * @author zc.ding
 * @date 2016年11月9日
 *
 */
public class CustJdbcAuthorizationCodeServices extends JdbcAuthorizationCodeServices {

	RandomValueStringGenerator generator = new RandomValueStringGenerator();
	private int length = 36;	//默认生成code的长度为36位
	
	public CustJdbcAuthorizationCodeServices(DataSource dataSource) {
		super(dataSource);
		generator = new RandomValueStringGenerator(length);
	}

	/**
	 * 指定code长度的构造方法
	 * @param dataSource
	 * @param length
	 */
	public CustJdbcAuthorizationCodeServices(DataSource dataSource, int length) {
		super(dataSource);
		this.length = length;
		generator = new RandomValueStringGenerator(length);
	}

	/**
	 * 重写父类的父类中方法，使用自定义长度的code生成器
	 */
	public String createAuthorizationCode(OAuth2Authentication authentication) {
		String code = generator.generate();
		store(code, authentication);
		return code;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
}
