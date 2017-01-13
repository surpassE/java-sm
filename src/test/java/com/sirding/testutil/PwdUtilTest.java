package com.sirding.testutil;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import com.sirding.core.utils.secure.PwdUtil;

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
			String msg = Hex.encodeHexString(hashed);
			System.out.println(msg);
			System.out.println(msg.length());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test2(){
		String msg = "sirding";
		String pwd = PwdUtil.encrypt(msg).toString();
		System.out.println(pwd);
	}

	@Test
	public void test3()	{
		String uuid = UUID.randomUUID().toString();
		String msg = Hex.encodeHexString(uuid.getBytes());
		System.out.println(uuid);
		System.out.println(uuid.length());
		System.out.println(msg);
		System.out.println(msg.length());
	}

}
