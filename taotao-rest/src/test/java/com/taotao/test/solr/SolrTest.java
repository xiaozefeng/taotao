package com.taotao.test.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {

	@Test
	public void addDocument() throws Exception{
		//创建连接
		HttpSolrServer solr = new HttpSolrServer("http://192.168.56.101:8080/solr");
		//创建文档
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品2");
		document.addField("item_price", 52310);
		//写入索引库
		solr.add(document);
		//提交关闭
		solr.commit();
	}
	
	@Test
	public void deleteDocument() throws Exception{
		//创建连接
		HttpSolrServer solr = new HttpSolrServer("http://192.168.56.101:8080/solr");
		//solr.deleteById("test001");
		solr.deleteByQuery("*:*");
		solr.commit();
	}
	
	@Test
	public void queryDocument() throws Exception{
		//创建连接
		HttpSolrServer solr = new HttpSolrServer("http://192.168.56.101:8080/solr");
		//创建查询对象
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		query.setStart(20);
		query.setRows(50);
		
		QueryResponse response = solr.query(query);
		SolrDocumentList list = response.getResults();
		for (SolrDocument document : list) {
			System.out.println(document.get("id"));
			System.out.println(document.get("item_title"));
			System.out.println(document.get("item_image"));
			System.out.println(document.get("item_price"));
		}
		
		
	}
	
}
