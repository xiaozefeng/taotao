package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
//
//	@RequestMapping(value= "/itemcat/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//	@ResponseBody
//	public String getItemcatList(String callback){
//		CatResult itemCatList = itemCatService.getItemCatList();
//		String json = JsonUtils.objectToJson(itemCatList);
//		String result = callback+"("+json+");";
//		return result;
//	}
	
	@RequestMapping("/itemcat/list")
	@ResponseBody
	public Object getItemCatList(String callback){
		CatResult itemCatList = itemCatService.getItemCatList();
		MappingJacksonValue result = new MappingJacksonValue(itemCatList);
		result.setJsonpFunction(callback);
		return result;
	}
}
