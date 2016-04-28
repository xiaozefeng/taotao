package com.taotao.service.impl;

import java.util.Date;
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
		
		List<TbItemParam> itemParams = tbItemParamMapper.selectByExample(example);
		/**有数据就返回数据，没有数据返回空*/
		if(itemParams != null && itemParams.size()>0){
			return TaotaoResult.ok(itemParams.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam tbItemParam) {
		//补全pojo
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		tbItemParamMapper.insert(tbItemParam);
		return TaotaoResult.ok();
	}

	@Override
	public EUDataGridResult getItemParamList(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<ItemParamDto> itemParams =tbItemParamMapper.getItemParamList();
		EUDataGridResult result =null;
		if(itemParams != null && itemParams.size()>0){
			result = new EUDataGridResult();
			result.setRows(itemParams);
			PageInfo<ItemParamDto> info = new PageInfo<>(itemParams);
			result.setTotal(info.getTotal());
		}
		return result;
	}
	
}
