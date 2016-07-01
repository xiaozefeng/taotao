package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
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
		List<TbContentCategory> list = selectContentCategoryByParentId(parentId);
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
	
	/**
	 * 根据parentId查询所有子节点
	 * @param parentId
	 * @return
	 */
	private List<TbContentCategory> selectContentCategoryByParentId(Long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		return list;
	}

	@Override
	public TaotaoResult createContentCategory(Long parentId, String name) {
		TbContentCategory category = new TbContentCategory();
		category.setParentId(parentId);
		category.setName(name);
		category.setSortOrder(1);
		category.setStatus(1);
		category.setIsParent(false);
		TbContentCategory parentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentCategory.getIsParent()){
			parentCategory.setIsParent(true);
			parentCategory.setUpdated(new Date());
			tbContentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
		category.setCreated(new Date());
		category.setUpdated(new Date());
		tbContentCategoryMapper.insert(category);
		return TaotaoResult.ok(category);
	}

	@Override
	public TaotaoResult deleteContentCategory(Long id) {
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
		tbContentCategoryMapper.deleteByPrimaryKey(id);
		//判断父节点下是否还有子节点
		List<TbContentCategory> list = selectContentCategoryByParentId(contentCategory.getParentId());
		if(list ==null || list.size()==0){
			TbContentCategory parentCategory = tbContentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
			parentCategory.setIsParent(false);
			parentCategory.setUpdated(new Date());
			tbContentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult updateContentCategory(TbContentCategory record) {
		tbContentCategoryMapper.updateByPrimaryKeySelective(record);
		return TaotaoResult.ok();
	}

}
