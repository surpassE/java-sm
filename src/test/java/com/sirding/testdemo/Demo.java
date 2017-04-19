package com.sirding.testdemo;

import java.math.BigDecimal;

import org.junit.Test;

public class Demo {

	@Test
	public void test1() {
		BigDecimal a = new BigDecimal(10000);
		System.out.println(a.compareTo(BigDecimal.ZERO) == 1);
		System.out.println(a.divideAndRemainder(new BigDecimal(100))[1].compareTo(BigDecimal.ZERO)==0);
	}
}
