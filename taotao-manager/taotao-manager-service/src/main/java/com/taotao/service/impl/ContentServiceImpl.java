package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper tbContentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_CONTENT_SYN_URL}")
	private String REST_CONTENT_SYN_URL;
	
	
	@Override
	public EUDataGridResult getContentList(int page,int rows,Long categoryId) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		PageHelper.startPage(page, rows);
		List<TbContent> list = tbContentMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		if(list != null && list.size()>0){
			result.setRows(list);
		}
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

	@Override
	public TaotaoResult insertContent(TbContent record) {
		record.setCreated(new Date());
		record.setUpdated(new Date());
		tbContentMapper.insert(record);
		try {
			//添加缓存同步
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYN_URL + record.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}

}
