package com.sirding.core.utils.secure;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class MD5Util {

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
	
	public static void main(String[] args) {
		String msg = "sirding";
		System.out.println("---------MD5----------------");
		System.out.println(new String(getMD5(msg.getBytes())));
		System.out.println(getMD5Hex(msg));
		System.out.println(getMD5Base64(msg));

	}
}
