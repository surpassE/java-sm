package com.sirding.core.utils.secure;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 对称加密工具类
 * @author zc.ding
 * @date 2016年10月14日
 */
public class DesUtil {
	
	private static byte[] DEFAULT_BYTES = { 1, -1, 1, -1, 1, -1, 1, -1 };
	//默认秘钥
	private static String DEFAULT_KEY = "51qiankundai.com";

	/**
	 * 以指定的字节数组做key对data进行加密
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param data
	 * @param key
	 * @return
	 */
	public static byte[] encrypt(byte[] data, byte[] key) {
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec desKeySpec = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(DEFAULT_BYTES);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			return cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 以指定的字节数组作为key对数据进行解密操作
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param data 要解密的数据
	 * @param key
	 * @return
	 */
	public static byte[] decrypt(byte[] data, byte[] key) {
		try {
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			DESKeySpec desKeySpec = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(DEFAULT_BYTES);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			return cipher.doFinal(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 用默认的Key对data进行加密操作
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param data
	 * @return
	 */
	public static String encrypt(String data) {
		return encrypt(data, DEFAULT_KEY);
	}
	
	/**
	 * 用指定Key对data进行加密操作
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param data
	 * @param key
	 * @return
	 */
	public static String encrypt(String data, String key) {
		try {
			byte[] bytes = encrypt(data.getBytes(), key.getBytes());
			return Base64.encodeBase64String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	/**
	 * 使用默认的key对data进行解密操作
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param data
	 * @return
	 */
	public static String decrypt(String data) {
		return decrypt(data, DEFAULT_KEY);
	}
	
	/**
	 * 使用指定的key对data进行解密操作
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param data
	 * @return
	 */
	public static String decrypt(String data, String key) {
		try {
			byte[] bytes = Base64.decodeBase64(data.getBytes("UTF-8"));
			bytes = decrypt(bytes, key.getBytes());
			return new String(bytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static void main(String[] args) {
		String msg = "i am sirding";
		String a = DesUtil.encrypt(msg);
		System.out.println(a);
		System.out.println("-------------------");
		System.out.println(DesUtil.decrypt(a));
	}

}
