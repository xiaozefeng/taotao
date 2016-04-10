package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;

@Service("itemService")
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Override
	public TbItem geTbItemById(long id) {
		TbItem selectByPrimaryKey = tbItemMapper.selectByPrimaryKey(id);
		return selectByPrimaryKey;
	}

	@Override
	public PageInfo<TbItem> getItemForPage(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<TbItem> items = tbItemMapper.getItemForPage();
		return new PageInfo<>(items);
	}
	/**
	 * 分页返回一个简单属性的分页
	 * <p>Title: getItemList</p>
	 * <p>Description: </p>
	 * @param page
	 * @param rows
	 * @return
	 * @see com.taotao.service.ItemService#getItemList(int, int)
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		PageHelper.startPage(page, rows);
		List<TbItem> items = tbItemMapper.getItemForPage();
		
		EUDataGridResult dataGridResult = new EUDataGridResult();
		dataGridResult.setRows(items);
		PageInfo<TbItem> pageinfo =new PageInfo<>(items);
		dataGridResult.setTotal(pageinfo.getTotal());
		
		return dataGridResult;
	}

	@Override
	public TaotaoResult addItem(TbItem item, String desc) throws Exception {
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte)1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		tbItemMapper.insertItem(item);
		TaotaoResult result = insertItemDesc(itemId,desc);
		if(result.getStatus() != 200){
			throw new Exception();
		}
		return result;
	}

	private TaotaoResult insertItemDesc(long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(itemId);
		tbItemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	
}
