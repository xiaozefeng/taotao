package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;

@Repository
public class SearchDaoImpl implements SearchDao{

	@Autowired
	private SolrServer solrServer;
	@Override
	public SearchResult search(SolrQuery solrQuery) throws Exception {
		//创建结果对象
		SearchResult searchResult = new SearchResult();
		//根据solrQuery查询商品数据
		QueryResponse query = solrServer.query(solrQuery);
		SolrDocumentList results = query.getResults();
		//设置总记录数
		searchResult.setRecordCount(results.getNumFound());
		//设置高亮显示
		Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
		
		List<Item> list = new ArrayList<>();
		for (SolrDocument document : results) {
			//设置列表数据
			Item item = new Item();
			item.setId((String) document.get("id"));
			//设置title高亮
			List<String> titles = highlighting.get(document.get("id")).get("item_title");
			String title = "";
			if(titles != null && titles.size()>0){
				title = titles.get(0);
			}else{
				title = (String) document.get("item_title");
			}
			item.setTitle(title);
			item.setImage((String) document.get("item_image"));
			item.setPrice((long) document.get("item_price"));
			item.setSell_point((String) document.get("item_sell_point"));
			item.setCategory_name((String) document.get("item_category_name"));
			
			list.add(item);
		}
		searchResult.setItemList(list);
		return searchResult;
	}

}
