package com.sirding.aop;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

public class Demo{

	@Test
	public void testMethodInterceptor() throws Exception {
//		String expression = "(execution(* com..service.impl.*Impl.*(..)))";
		String expression = "execution(* com.sirding.aop.HelloWorld.*(..))";
		AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
		aspectJExpressionPointcut.setExpression(expression);
		Method method = HelloWorld.class.getDeclaredMethod("insertUser");
		System.out.println("获得方法名称：" + method.getName());
		Class<?> clazz = HelloWorld.class;
		boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(method,clazz);
		Assert.assertTrue(matches);
	}
}
