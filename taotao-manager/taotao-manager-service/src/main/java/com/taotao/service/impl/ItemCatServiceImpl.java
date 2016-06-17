package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;


@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		List<EUTreeNode> euList =null;
		if(list != null && list.size()>0){
			euList = new ArrayList<>();
			for (TbItemCat ti : list) {
				EUTreeNode eu = new EUTreeNode();
				eu.setId(ti.getId());
				eu.setText(ti.getName());
				eu.setState(ti.getIsParent()?"closed":"open");
				euList.add(eu);
			}
		}
		return euList;
	}

}
