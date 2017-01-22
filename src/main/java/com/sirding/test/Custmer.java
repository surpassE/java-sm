package com.sirding.test;

import com.sirding.core.utils.ActiveMqUtil;

public class Custmer {

	public static void main(String[] args) {
		ActiveMqUtil.listen("myQueue", true);
	}
}
