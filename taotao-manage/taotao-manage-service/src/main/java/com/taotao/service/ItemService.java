package com.taotao.service;

import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	/**
	 * 根据主键查询商品信息
	 * <p>Title: geTbItemById</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 */
	public TbItem geTbItemById(long id);
	/**
	 * 分页查询
	 * <p>Title: getItemForPage</p>
	 * <p>Description: </p>
	 * @return
	 */
	public PageInfo<TbItem> getItemForPage(int pageNum, int pageSize);
	/**
	 * 查询商品列表
	 * <p>Title: getItemList</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return
	 */
	public EUDataGridResult getItemList(int page,int rows);
	/**
	 * 保存商品信息很商品描述
	 * <p>Title: addItem</p>
	 * <p>Description: </p>
	 * @param item
	 * @param desc
	 * @return
	 */
	public TaotaoResult addItem(TbItem item, String desc)throws Exception;
}
