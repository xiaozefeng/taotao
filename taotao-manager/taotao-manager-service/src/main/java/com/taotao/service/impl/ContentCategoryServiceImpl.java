package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;
/**
 * 内容分类service
 * @author xiaozefeng
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<EUTreeNode> getContentCategoryList(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		List<EUTreeNode> result = new ArrayList<>(); 
		if(list != null && list.size()>0){
			for (TbContentCategory tbc : list) {
				EUTreeNode eu = new EUTreeNode();
				eu.setId(tbc.getId());
				eu.setText(tbc.getName());
				eu.setState(tbc.getIsParent()?"closed":"open");
				result.add(eu);
			}
		}
		return result;
	}

}
