package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
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
	/***
	 * 保存商品信息和商品描述
	 * @param item
	 * @return
	 */
	TaotaoResult saveItem(TbItem item,String desc)throws Exception;
	
	
	
}
