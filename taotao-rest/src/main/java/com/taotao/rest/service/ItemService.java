package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {
	/**
	 * 根据商品id获取商品信息
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemInfo(Long itemId);
	/***
	 * 根据商品id查询商品描述
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemDesc(Long itemId);
	/***
	 * 根据商品id查询规格参数
	 * @param itemId
	 * @return
	 */
	TaotaoResult getItemParam(Long itemId);
}
