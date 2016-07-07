package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;
/**
 * 导入商品到solr 索引库
 * @author xiaozefeng
 *
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/importAll")
	@ResponseBody
	public TaotaoResult importAllItem(){
		TaotaoResult result = itemService.importAll();
		return result; 
	}
}
