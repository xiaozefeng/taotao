package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String getItemInfo(@PathVariable Long itemId,Model model){
		ItemInfo itemInfo = itemService.getItemInfo(itemId);
		model.addAttribute("item", itemInfo);
		return "item";
	}
	
	@RequestMapping(value= "/item/desc/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDesc(@PathVariable Long itemId){
		String itemDesc = itemService.getItemDesc(itemId);
		return itemDesc;
	}
	
	@RequestMapping(value= "/item/param/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParam(@PathVariable Long itemId){
		String itemParamItem = itemService.getItemParamItem(itemId);
		return itemParamItem;
	}
}
