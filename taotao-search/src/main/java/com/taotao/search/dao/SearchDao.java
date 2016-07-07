package com.taotao.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.taotao.search.pojo.SearchResult;

public interface SearchDao {
	/***
	 * 根据查询对象从索引库查询数据
	 * @param solrQuery
	 * @return
	 */
	SearchResult search(SolrQuery solrQuery)throws Exception;
}
