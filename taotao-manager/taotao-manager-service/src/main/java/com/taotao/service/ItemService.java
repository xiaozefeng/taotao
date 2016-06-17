package com.taotao.service;

import com.taotao.pojo.TbItem;

public interface ItemService {
	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 */
	TbItem getItemById(Long id);
}
