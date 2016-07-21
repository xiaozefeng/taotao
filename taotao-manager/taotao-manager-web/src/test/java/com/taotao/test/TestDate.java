package com.taotao.test;

import org.joda.time.DateTime;
import org.junit.Test;

public class TestDate {

	@Test
	public void testDate(){
		String string = new DateTime().toString("/yyyy/MM/dd");
		System.out.println(string);
	}
}
