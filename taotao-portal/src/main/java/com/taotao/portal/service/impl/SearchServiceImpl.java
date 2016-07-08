package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
/***
 * 搜索服务
 * @author xiaozefeng
 *
 */
@Service
public class SearchServiceImpl implements SearchService{
	
	@Value("${SOLR_BASE_URL}")
	private String SOLR_BASE_URL;
	@Override
	public SearchResult search(String queryString, Integer page) {
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page+"");
		try {
			String json = HttpClientUtil.doGet(SOLR_BASE_URL, param);
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
			SearchResult searchResult = (SearchResult) taotaoResult.getData();
			return searchResult;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
