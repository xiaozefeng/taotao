package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

public interface SearchService {
	/**
	 * 商品搜索
	 * @param queryString
	 * @param page
	 * @return
	 */
	SearchResult search(String queryString,Integer page);
}
