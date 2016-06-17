package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	
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

}
