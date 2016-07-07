package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

public interface SearchService {
	/***
	 * 搜索服务service
	 * @param queryString
	 * @param page
	 * @param rows
	 * @return
	 */
	SearchResult search(String queryString,int page,int rows)throws Exception;
}
