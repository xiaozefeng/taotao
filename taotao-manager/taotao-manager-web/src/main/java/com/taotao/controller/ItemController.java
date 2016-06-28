package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemservice;
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){
		return itemservice.getItemById(itemId);
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EUDataGridResult getItemList(int page,int rows){
		return itemservice.getItemList(page, rows);
	}
	
	@RequestMapping(value = "item/save",method= RequestMethod.POST)
	@ResponseBody
	public TaotaoResult itemSave(TbItem item,String desc,String itemParams)throws Exception{
		return itemservice.saveItem(item,desc,itemParams);
	}
	
}
