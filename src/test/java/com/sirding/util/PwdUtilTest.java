package com.sirding.util;

import java.security.MessageDigest;

import org.apache.shiro.codec.Hex;
import org.junit.Test;

public class PwdUtilTest {

	private static final int DEFAULT_ITERATIONS = 1;
	
	
	@Test
	public void test(){
		byte[] bytes = "123".getBytes();
		byte[] salt = "i am sirding".getBytes();
		try {
//			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			MessageDigest digest = MessageDigest.getInstance("MD5");
			if (salt != null) {
	            digest.reset();
//	            digest.update(salt);
	        }
	        byte[] hashed = digest.digest(bytes);
	        int iterations = 1 - DEFAULT_ITERATIONS; //already hashed once above
	        //iterate remaining number:
	        for (int i = 0; i < iterations; i++) {
	            digest.reset();
	            hashed = digest.digest(hashed);
	        }
	        String msg = Hex.encodeToString(hashed);
	        System.out.println(msg);
	        System.out.println(msg.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
