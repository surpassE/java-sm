package com.sirding.security.shiro.cryptography;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.HashService;
import org.apache.shiro.util.ByteSource;

/**
 * 基于shiro的密码加密方式
 * @author zc.ding
 * @date 2016年10月14日
 */
public class PasswordShiroUtil {
	//默认加密方式
	private static final String DEFAULT_ALGORITHMNAME = "SHA-512";
	//默认盐源
	private static final String DEFAULT_SALT = Hex.encodeToString("I am siridng.".getBytes());
	//加密循环次数
	private static final int DEFAULT_ITERATIONS = 1;
	
	/**
	 * 默认加密方式：采用SHA-512、使用默认的盐值，循环加密1次
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param password
	 * @return
	 */
	public static String encrypt(String password){
		return encrypt(password, DEFAULT_ALGORITHMNAME, DEFAULT_SALT, DEFAULT_ITERATIONS);
	}
	
	/**
	 * 指定加密算法(MD5、SHA-1、SHA-128)对password进行加密，使用默认的盐值，循环加密1次
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param password
	 * @param algorithmName
	 * @return
	 */
	public static String encrypt(String password, String algorithmName){
		return encrypt(password, algorithmName, DEFAULT_SALT, DEFAULT_ITERATIONS);
	}
	
	/**
	 * 指定加密算法(MD5、SHA-1、SHA-128)、盐值对password进行加密的操作，循环加密1次
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param password
	 * @param algorithmName
	 * @param salt
	 * @return
	 */
	public static String encrypt(String password, String algorithmName, String salt){
		return encrypt(password, algorithmName, salt, DEFAULT_ITERATIONS);
	}
	
	/**
	 * 指定加密算法(MD5、SHA-1、SHA-128)、盐值、加密循环的次数对password进行加密
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param password
	 * @param algorithmName
	 * @param salt
	 * @param iterations
	 * @return
	 */
	public static String encrypt(String password, String algorithmName, String salt, int iterations){
		HashService hashService = new DefaultHashService();
		HashRequest request = getRequest(algorithmName, password, salt, iterations);
		Hash hash = hashService.computeHash(request);
		return hash.toString();
	}
	
	/**
	 * 实例化加密环境
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param algorithmName
	 * @param msg
	 * @param salt
	 * @param iterations
	 * @return
	 */
	private static HashRequest getRequest(String algorithmName, String msg, String salt, int iterations){
		HashRequest request = new HashRequest.Builder()
                .setAlgorithmName(algorithmName)
                .setSource(ByteSource.Util.bytes(msg))
                .setSalt(ByteSource.Util.bytes(salt))
                .setIterations(iterations)
                .build();
		return request;
	}
	
	public static void main(String[] args) {
		String password = "I am sirding.";
		String result = "";
		System.out.println("原密码：" + password);
		System.out.println("---------采用默认加密方式------------");
		result = encrypt(password);
		System.out.println("加密后：" + result);
		System.out.println("密码长度：" + result.length());
		System.out.println("---------指定MD5加密算法--------------");
		result = encrypt(password, "MD5");
		System.out.println("加密后：" + result);
		System.out.println("---------指定MD5算法、盐值-------------");
		result = encrypt(password, "MD5", "Hello world");
		System.out.println("加密后：" + result);
		System.out.println("---------指定MD5算法、盐值、加密次数-------------");
		result = encrypt(password, "MD5", "Hello world", 10);
		System.out.println("加密后：" + result);
	}

}
