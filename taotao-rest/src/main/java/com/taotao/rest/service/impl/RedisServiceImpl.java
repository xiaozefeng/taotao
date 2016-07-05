package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.constant.RedisConstant;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService{

	@Autowired
	private JedisClient jedisClient;
		
	
	@Override
	public TaotaoResult synContent(Long contentCid) {
		jedisClient.hdel(RedisConstant.INDEX_CONTENT_REDIS_KEY, contentCid+"");
		return TaotaoResult.ok();
	}

}
