package com.taotao.search.mapper;

import java.util.List;

import com.taotao.search.projo.Item;

public interface ItemMapper {
	/***
	 * 查询索引商品信息
	 * @return
	 */
	List<Item> getItemList();
}
