package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	/***
	 * 加载内容列表
	 * @param categoryId
	 * @return
	 */
	EUDataGridResult getContentList(int page,int rows,Long categoryId);
	/**
	 * 保存商品内容信息
	 * @param record
	 * @return
	 */
	TaotaoResult insertContent(TbContent record);
}
