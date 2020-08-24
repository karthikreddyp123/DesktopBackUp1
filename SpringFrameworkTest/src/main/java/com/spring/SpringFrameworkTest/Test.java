package com.spring.SpringFrameworkTest;

public class Test {
	public Test(int temp,Test test) {
		super();
		this.temp = temp;
		this.test=test;
	}

	private int temp;
	private Test test;
	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public void print() {
		test.test();
		System.out.println("Hello");
	}
	public void test() {
		System.out.println("HelloTest");
	}
}
