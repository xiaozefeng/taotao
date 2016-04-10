/*package com.test.Controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/applicationContext-*.xml"})
public class TestItemServiceImpl2 {
	@Autowired
	private ItemService itemService;
	
	@Test
	public void testService(){
		EUDataGridResult itemList = itemService.getItemList(1, 10);
		List<TbItem> rows = (List<TbItem>) itemList.getRows();
		if(rows != null && rows.size()>0){
			for (TbItem tbItem : rows) {
				System.out.println(tbItem.getTitle());
			}
			//System.out.println(JSON.toJSONString(users,SerializerFeature.PrettyFormat));
		}
	}
}
*/