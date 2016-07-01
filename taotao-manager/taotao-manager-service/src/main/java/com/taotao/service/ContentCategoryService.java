package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;

public interface ContentCategoryService {
	/**
	 * 获取内容分类tree
	 * @param parentId
	 * @return
	 */
	List<EUTreeNode> getContentCategoryList(Long parentId);
}
