package com.test.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.dto.ItemParamDto;
import com.taotao.service.ItemParamService;
import com.test.baseTest.TestSpringCase;

public class ItemParamServiceImpl extends TestSpringCase {
	@Autowired
	private ItemParamService itemParamService;
	
	@Test
	public void testItemParamServiceCase(){
		EUDataGridResult eug = itemParamService.getItemParamList(1, 30);
		List<ItemParamDto> rows = (List<ItemParamDto>) eug.getRows();
		for (ItemParamDto itemParamDto : rows) {
			System.out.println(itemParamDto);
		}
	}
}
