package com.taotao.test;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

public class TestDate {

	/**
	 * datetime和string的转换
	 */
	@Test
	public void testDate(){
		String string = new DateTime().toString("/yyyy/MM/dd");
		System.out.println(string);
	}
	
	/**
	 *datetime和date的转换
	 */
	@Test
	public void testJodaTime(){
		DateTime dateTime = new DateTime(1414489420444L);
		Date date = dateTime.toDate();
		System.out.println(dateTime);
	}
}
