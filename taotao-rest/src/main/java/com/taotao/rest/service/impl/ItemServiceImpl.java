package com.taotao.rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamItemExample.Criteria;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private TbItemParamItemMapper tbItemParamItemMapper; 
	
	@Override
	public TaotaoResult getItemInfo(Long itemId) {
		String key = REDIS_ITEM_KEY+":"+itemId+":base";
		try {
			String json = jedisClient.get(key);
			if(StringUtils.isNoneBlank(json)){
				TbItem tbitem = JsonUtils.jsonToPojo(json, TbItem.class);
				return TaotaoResult.ok(tbitem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
		
		try {
			String json = JsonUtils.objectToJson(item);
			jedisClient.set(key, json);
			jedisClient.expire(key, REDIS_ITEM_EXPIRE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(item);
	}

	@Override
	public TaotaoResult getItemDesc(Long itemId) {
		String key = REDIS_ITEM_KEY+":"+itemId+":desc";
		try {
			String json = jedisClient.get(key);
			if(StringUtils.isNotBlank(json)){
				TbItemDesc desc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return TaotaoResult.ok(desc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		
		try {
			String json = JsonUtils.objectToJson(itemDesc);
			jedisClient.set(key, json);
			jedisClient.expire(key, REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(itemDesc);
	}

	@Override
	public TaotaoResult getItemParam(Long itemId) {
		String key = REDIS_ITEM_KEY+":"+itemId+":param";
		try {
			String json = jedisClient.get(key);
			TbItemParamItem jsonObject = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
			if(jsonObject != null){
				return TaotaoResult.ok(jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbItemParamItemExample example =new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
		TbItemParamItem result = new TbItemParamItem();
		if(list != null && list.size()>0){
			result = list.get(0);
			
			try {
				String json = JsonUtils.objectToJson(result);
				jedisClient.set(key, json);
				jedisClient.expire(key, REDIS_ITEM_EXPIRE);
				return TaotaoResult.ok(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return TaotaoResult.build(400, "无此商品规格参数");
	}

}
