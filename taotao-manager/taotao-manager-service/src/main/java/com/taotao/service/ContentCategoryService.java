package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;

public interface ContentCategoryService {
	/**
	 * 获取内容分类tree
	 * @param parentId
	 * @return
	 */
	List<EUTreeNode> getContentCategoryList(Long parentId);
	/**
	 * 创建新的内容分类节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	TaotaoResult createContentCategory(Long parentId,String name);
	/***
	 * 删除内容分类节点
	 * @param parentId
	 * @param id
	 * @return
	 */
	TaotaoResult deleteContentCategory(Long id);
	/**
	 * 修改内容分类节点
	 * @param record
	 * @return
	 */
	TaotaoResult updateContentCategory(TbContentCategory record);
}
