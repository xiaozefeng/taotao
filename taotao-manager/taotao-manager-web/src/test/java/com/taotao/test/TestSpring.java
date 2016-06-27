package com.taotao.test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.dto.ItemParamDto;
import com.taotao.mapper.TbItemParamMapper;

public class TestSpring {
	private static ApplicationContext ac; 
	
	@BeforeClass
	public static void initApplication(){
		ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		
	}
	@AfterClass
	public static void destoryApplication(){
		
	}
	
	@Test
	public   void testCase(){
		TbItemParamMapper bean = (TbItemParamMapper) ac.getBean("tbItemParamMapper");
		List<ItemParamDto> list = bean.getItemParamForPage();
		for (ItemParamDto itemParamDto : list) {
			System.out.println(itemParamDto.getItemCatName());
			
		}
	}
	
}
