package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {
	/***
	 * 导入商品数据到solr索引库
	 * @return
	 */
	public TaotaoResult importAll();
}
