package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 */
	TbItem getItemById(Long id);
	/**
	 * 获取商品列表
	 * @param page
	 * @param rows
	 * @return
	 */
	EUDataGridResult getItemList(int page,int rows);
	
	
	
}
