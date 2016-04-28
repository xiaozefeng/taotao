package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
	/**
	 * 根据商品分类id查询规格参数
	 * @param cid
	 * @return
	 * * @date 2016年4月27日下午3:53:29
	 */
	public TaotaoResult getItemParamByCid(long cid);
	/**
	 * 保存规格参数
	 * @param tbItemParam
	 * @return
	 * * @date 2016年4月27日下午4:17:13
	 */
	public TaotaoResult insertItemParam(TbItemParam tbItemParam);
	/**
	 * 规格参数列表
	 * @param page
	 * @param rows
	 * @return
	 * * @date 2016年4月28日下午1:45:24
	 */
	public EUDataGridResult getItemParamList(Integer page, Integer rows);
}
