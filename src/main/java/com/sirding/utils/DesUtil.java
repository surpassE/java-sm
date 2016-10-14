package com.sirding.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
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
	 * 获得source的md5对应的byte数组
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param source
	 * @return
	 */
	public static byte[] getMD5(byte[] source) {
		byte tmp[] = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			tmp = md.digest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}
	
	/**
	 * 获得16进制格式MD5值
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param data
	 * @return
	 */
	public static String getMD5Hex(String data){
		return Hex.encodeHexString(getMD5(data.getBytes()));
	}
	
	/**
	 * 获得Base64编码的MD5值
	 * @date 2016年10月14日
	 * @author zc.ding
	 * @param data
	 * @return
	 */
	public static String getMD5Base64(String data){
		return Base64.encodeBase64String(getMD5(data.getBytes()));
	}

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
		
		System.out.println("---------MD5----------------");
		System.out.println(new String(getMD5(msg.getBytes())));
		System.out.println(getMD5Hex(msg));
		System.out.println(getMD5Base64(msg));
		
	}

}
