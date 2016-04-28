package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId){
		return itemParamService.getItemParamByCid(itemCatId);
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult saveItemParam(@PathVariable Long cid ,String paramData){
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		return itemParamService.insertItemParam(itemParam);
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult ItemParamList(Integer page ,Integer rows){
		return itemParamService.getItemParamList(page,rows);
	}
	
	
	
}
