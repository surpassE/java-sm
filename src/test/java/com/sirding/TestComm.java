package com.sirding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestComm {

	@Test
	public void test(){
		String msg = "loginName";
		String reg = "[A-Z]";
		Pattern pattern = Pattern.compile(reg);
		Matcher m = pattern.matcher(msg);
		while(m.find()){
			String tmp = m.group();
			msg = msg.replaceAll(tmp, "_" + tmp.toLowerCase());
		}
		System.out.println(msg);
	}
}
