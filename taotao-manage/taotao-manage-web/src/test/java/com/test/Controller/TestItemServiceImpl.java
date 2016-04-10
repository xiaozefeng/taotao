package com.test.Controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
/**
 * 
 * <p>Title: TestItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.ewaytec.cn</p> 
 * @author	肖泽锋
 * @date	2016年4月6日下午12:39:06
 * @version 1.0
 */
public class TestItemServiceImpl {
	/*protected ApplicationContext ac;
	
	@Before
	public void initApplicationContext(){
		ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
	}*/
	
	/*@Test
	public void testMapper(){
		ItemService itemservice = (ItemService) ac.getBean("itemService");
		EUDataGridResult egr = itemservice.getItemList(1, 10);
		List<TbItem> rows = (List<TbItem>) egr.getRows();
		for (TbItem tbItem : rows) {
			System.out.println(tbItem.getTitle());
		}
	}*/
}
