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
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public TbItem getItemById(Long id) {
		//TbItem item = tbItemMapper.selectByPrimaryKey(id);
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
	    criteria.andIdEqualTo(id);
	    List<TbItem> list = tbItemMapper.selectByExample(example);
	    if( list != null && list.size()>0){
	    	return list.get(0);
	    }
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		TbItemExample example = new TbItemExample();
		//开始分页处理
		PageHelper.startPage(page, rows);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//获取总记录数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public TaotaoResult saveItem(TbItem item,String desc,String itemParams) throws Exception {
		//补全商品属性
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		tbItemMapper.insert(item);
		
		//添加商品描述
		TaotaoResult result = saveItemDesc(desc, itemId);
		if(result.getStatus() != 200){
			throw new Exception();
		}
		//添加商品规格
		result = saveItemParamItem(itemParams, itemId);
		if(result.getStatus() != 200){
			throw new Exception();
		}
		return TaotaoResult.ok();
	}
	/**
	 * 添加商品描述
	 * @param desc
	 * @param itemId
	 * @return
	 */
	private TaotaoResult saveItemDesc(String desc, long itemId) {
		//保存商品描述信息
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(itemId);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		tbItemDescMapper.insert(itemDesc);
		return TaotaoResult.ok();
	}
	
	private TaotaoResult saveItemParamItem(String itemParams,Long itemId){
		TbItemParamItem record = new TbItemParamItem();
		record.setCreated(new Date());
		record.setUpdated(new Date());
		record.setParamData(itemParams);
		record.setItemId(itemId);
		tbItemParamItemMapper.insert(record);
		return TaotaoResult.ok();
	}
	
}
