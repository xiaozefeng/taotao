package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.constant.RedisConstant;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private TbContentMapper tbContentMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public List<TbContent> getContentList(Long contentCatgoryId) {
		
		try {
			String result = jedisClient.hget(RedisConstant.INDEX_CONTENT_REDIS_KEY, contentCatgoryId+"");
			if(StringUtils.isNotBlank(result)){
				List<TbContent> jsonToList = JsonUtils.jsonToList(result, TbContent.class);
				return jsonToList;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCatgoryId); 
		List<TbContent> list = tbContentMapper.selectByExample(example);
		
		try {
			//把数据放入redis缓存
			String cacheString = JsonUtils.objectToJson(list);
			jedisClient.hset(RedisConstant.INDEX_CONTENT_REDIS_KEY, contentCatgoryId+"", cacheString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
