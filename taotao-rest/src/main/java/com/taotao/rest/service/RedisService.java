package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

public interface RedisService {
	/**
	 * 同步redis缓存
	 * @param contentCid
	 * @return
	 */
	TaotaoResult synContent(Long contentCid);
}
