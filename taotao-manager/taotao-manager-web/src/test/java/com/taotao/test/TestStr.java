package com.taotao.test;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.Seconds;
import org.junit.Test;

import com.sun.tools.internal.xjc.model.SymbolSpace;
import com.taotao.common.utils.IDUtils;

public class TestStr {
	@Test
	public void testCase1(){
		String oldName = "abc.jpg";
		String newName = IDUtils.genImageName()+oldName.substring(oldName.indexOf("."));
		System.out.println(newName);

	}
	//使用joda计算时间差   天数
	@Test
	public void testJodaTime(){
		LocalDate start = new LocalDate(2012,12,14);
		LocalDate end = new LocalDate(2013,01,15);
		int days = Days.daysBetween(start, end).getDays();
		System.out.println(days);
	}
	
	@Test
	public void testJodaSeconds() throws Exception{
		//DateTime start = new DateTime(new Date());
		DateTime start = DateTime.now();
//		int sum =0 ;
//		for (int i = 0; i <10000; i++) {
//			sum+= i;
//		}
		Thread.sleep(10000L);
		DateTime end = DateTime.now();
		
		int seconds = Seconds.secondsBetween(start, end).getSeconds();
		System.out.println(seconds);
	}
	
}
