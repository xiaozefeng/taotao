package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

public interface ItemParamService {
	/***
	 * 根据商品类目id查询规格参数
	 * @param cid
	 * @return
	 */
	TaotaoResult getItemParamByCid(long cid);
	/**
	 * 查询规格参数列表
	 * @return
	 */
	EUDataGridResult getItemParamForPage(int page,int rows);
}
