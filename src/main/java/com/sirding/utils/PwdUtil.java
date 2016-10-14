package com.sirding.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * 基于java.security.MessageDigest + Base64实现密码加盐、加密操作
 * @author zc.ding
 * @date 2016年10月14日
 */
public class PwdUtil {
	//默认加密方式
	private static final String DEFAULT_ALGORITHM = "SHA-512";
	//默认盐源
	private static final String DEFAULT_SALT = Hex.encodeHexString("I am siridng.".getBytes());
	//加密循环次数
	private static final int DEFAULT_ITERATIONS = 1;

	/**
	 * 默认加密方式：采用SHA-512、使用默认的盐值，循环加密1次
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param password
	 * @return
	 */
	public static ResultType encrypt(String password){
		return encrypt(password, DEFAULT_ALGORITHM, DEFAULT_SALT, DEFAULT_ITERATIONS);
	}

	/**
	 * 指定加密算法(MD5、SHA-1、SHA-128)对password进行加密，使用默认的盐值，循环加密1次
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param password
	 * @param algorithm
	 * @return
	 */
	public static ResultType encrypt(String password, String algorithm){
		return encrypt(password, algorithm, DEFAULT_SALT, DEFAULT_ITERATIONS);
	}

	/**
	 * 指定加密算法(MD5、SHA-1、SHA-128)、盐值对password进行加密的操作，循环加密1次
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param password
	 * @param algorithm
	 * @param salt
	 * @return
	 */
	public static ResultType encrypt(String password, String algorithm, String salt){
		return encrypt(password, algorithm, salt, DEFAULT_ITERATIONS);
	}

	/**
	 * 指定加密算法(MD5、SHA-1、SHA-128)、盐值、加密循环的次数对password进行加密
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param password
	 * @param algorithm
	 * @param salt
	 * @param iterations
	 * @return
	 */
	public static ResultType encrypt(String password, String algorithm, String salt, int iterations){
		byte[] bytes = password.getBytes();
		byte[] saltBytes = salt.getBytes();
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(algorithm);
			if (salt != null) {
				digest.reset();
				digest.update(saltBytes);
			}
			byte[] hashed = digest.digest(bytes);
			iterations = iterations - DEFAULT_ITERATIONS; //already hashed once above
			//iterate remaining number:
			for (int i = 0; i < iterations; i++) {
				digest.reset();
				hashed = digest.digest(hashed);
			}
			return new ResultType(hashed);	//要求ResultType必须是静态(static)内存类
//			return new PwdUtil().new ResultType(hashed);	//不要求ResultType是静态内存类，但每次调用都需要额外创建PwdUtil对象
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 用于控制以何种方式封装加密后的数据16进制或是Base64方式
	 * @author zc.ding
	 * @date 2016年10月14日
	 */
	public static class ResultType{
		private byte[] buff = null;
		private String msg = "";
		
		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public ResultType(){}
		
		public ResultType(byte[] buff){
			this.buff = buff;
		}
		
		public ResultType(String msg){
			this.msg = msg;
		}
		
		/**
		 * 返回原始字节数据
		 * @date 2016年10月14日
		 * @author zc.ding
		 * @return
		 */
		public byte[] getBytes(){
			return this.buff;
		}
		
		/**
		 * 以16进制方式显示加密后数据
		 * @date 2016年10月14日
		 * @author zc.ding
		 * @return
		 */
		public String toHex(){
			return Hex.encodeHexString(this.buff);
		}
		
		/**
		 * 以Base64方式显示加密后数据
		 * @date 2016年10月14日
		 * @author zc.ding
		 * @return
		 */
		public String toBase64(){
			return Base64.encodeBase64String(this.buff);
		}
		
		/**
		 * 以16进制方式显示加密后数据，调用了toHex()
		 */
		public String toString(){
			return this.toHex();
		}
	}
	
	public static void main(String[] args) {
		String password = "I am sirding.";
		String result = "";
		System.out.println("原密码：" + password);
		System.out.println("---------采用默认加密方式------------");
		result = encrypt(password).toString();
		System.out.println("加密后：" + result);
		System.out.println("密码长度：" + result.length());
		System.out.println("---------指定MD5加密算法--------------");
		result = encrypt(password, "MD5").toBase64();
		System.out.println("加密后：" + result);
		System.out.println("---------指定MD5算法、盐值-------------");
		result = encrypt(password, "MD5", "Hello world").toString();
		System.out.println("加密后：" + result);
		System.out.println("---------指定MD5算法、盐值、加密次数-------------");
		result = encrypt(password, "MD5", "Hello world", 10).toHex();
		System.out.println("加密后：" + result);
	}
}
