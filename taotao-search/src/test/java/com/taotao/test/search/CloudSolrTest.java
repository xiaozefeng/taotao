package com.taotao.test.search;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class CloudSolrTest {

	@Test
	public void addDocument() throws SolrServerException, IOException{
		String zkHost = "192.168.143.131:2181,192.168.143.131:2182,192.168.143.131:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		solrServer.setDefaultCollection("collection2");
		//创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		//添加文档
		document.addField("id", "123");
		document.addField("item_title", "test001");
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
	
	@Test
	public void delDocument() throws SolrServerException, IOException{
		String zkHost = "192.168.143.131:2181,192.168.143.131:2182,192.168.143.131:2183";
		CloudSolrServer solrServer = new CloudSolrServer(zkHost);
		solrServer.setDefaultCollection("collection2");
		//删除所有
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
}
