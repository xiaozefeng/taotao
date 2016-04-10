package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;

public interface ItemCatService {
	/**
	 * 加载商品分类列表
	 * <p>Title: getItemCatList</p>
	 * <p>Description: </p>
	 * @param parentId
	 * @return
	 */
	public List<EUTreeNode> getItemCatList(long parentId);
}
