package com.test.Controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

/**
 * 测试分页
 * <p>Title: TestPageHelper</p>
 * <p>Description: </p>
 * <p>Company: www.ewaytec.cn</p> 
 * @author	肖泽锋
 * @date	2016年4月3日下午10:45:59
 * @version 1.0
 */
public class TestPageHelper {
	/*private ApplicationContext ac =null;
	@Before
	public void loadApplicationContext(){
		ac =new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
	}
	
	@Test
	public void testPageHelper(){
		//获取Mapper的代理对象
		TbItemMapper mapper = ac.getBean(TbItemMapper.class);
		//执行查询方法
		PageHelper.startPage(2, 10);
		List<TbItem> items = mapper.getItemForPage();
		
		if(items != null){
			for (TbItem tbItem : items) {
				System.out.println(tbItem);
			}
		}
		
		PageInfo<TbItem> pageinfo =new PageInfo<>(items);
		long total = pageinfo.getTotal();
		System.out.println("一共有"+total+"条信息");
		
	}*/
}
