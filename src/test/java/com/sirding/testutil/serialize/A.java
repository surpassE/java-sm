package com.sirding.testutil.serialize;

import java.io.Serializable;

public class A implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	B b;
	private String name;
	private Integer age;
	public B getB() {
		return b;
	}
	public void setB(B b) {
		this.b = b;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
