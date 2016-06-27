package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.dto.ItemParamDto;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper; 
	
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = tbItemParamMapper.selectByExample(example);
		if(list != null && list.size()>0 ){
			return TaotaoResult.ok(list.get(0));
			
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public EUDataGridResult  getItemParamForPage(int page ,int rows) {
		//开始分页
		PageHelper.startPage(page, rows);
		List<ItemParamDto> list = tbItemParamMapper.getItemParamForPage();
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		
		PageInfo<ItemParamDto> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

}
