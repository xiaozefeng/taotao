package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

public interface ItemService {
	/**
	 * 获取商品信息
	 * @param itemId
	 * @return
	 */
	ItemInfo getItemInfo(Long itemId);	
	/***
	 * 获取商品描述信息
	 * @param itemId
	 * @return
	 */
	String getItemDesc(Long itemId);
	
	/**
	 * 获取商品规格参数信息
	 * @param itemId
	 * @return
	 */
	String getItemParamItem(Long itemId);
}
