package com.taotao.test;

import org.junit.Test;

public class TestException {

	@Test
	public void test1(){
		try {
			int i = 1/0;
		} catch (Exception e) {
		}
		System.out.println("hehe");
	}
}
