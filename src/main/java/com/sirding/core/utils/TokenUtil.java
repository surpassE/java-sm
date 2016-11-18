package com.sirding.core.utils;

import java.util.UUID;

import com.sirding.core.utils.secure.PwdUtil;

/**
 * @Described	: Token生成器
 * @project		: com.sirding.core.utils.TokenUtil
 * @author 		: zc.ding
 * @date 		: 2016年11月18日
 */
public class TokenUtil {

	/**
	 * @Described	: 获得Token
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: String
	 */
	public static String getToken(){
		String token = UUID.randomUUID().toString() + "-" +System.currentTimeMillis();
		return token;
	}
	
	/**
	 * @Described	: 指定生成token时使用的算法
	 * @author		: zc.ding
	 * @date 		: 2016年11月18日
	 * @return		: String
	 * @param algorithm	算法  MD5 SHA-1 SHA-256 SHA-512
	 * @return
	 */
	public static String getToken(String algorithm){
		String token = UUID.randomUUID().toString() + "-" +System.currentTimeMillis();
		token = PwdUtil.encrypt(token, algorithm).toHex();
		return token;
	}
}
