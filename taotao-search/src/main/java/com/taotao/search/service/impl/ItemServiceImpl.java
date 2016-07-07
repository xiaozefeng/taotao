package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.projo.Item;
import com.taotao.search.service.ItemService;

public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private HttpSolrServer httpSolrServer;
	
	@Override
	public TaotaoResult importAll() {
		try {
			List<Item> list = itemMapper.getItemList();
			for (Item item : list) {
				SolrInputDocument document = new SolrInputDocument();
				document.setField("id", item.getId());
				document.setField("item_title", item.getTitle());
				document.setField("item_sell_point", item.getSell_point());
				document.setField("item_price", item.getPrice());
				document.setField("item_image", item.getImage());
				document.setField("item_category_name", item.getCategory_nmae());
				document.setField("item_desc", item.getItem_desc());
				//添加到solr索引库
				httpSolrServer.add(document);
			}
			//提交
			httpSolrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}

}
