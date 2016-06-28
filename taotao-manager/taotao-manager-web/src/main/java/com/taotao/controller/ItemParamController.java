package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 规格参数controller
 * @author xiaozefeng
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	/**
	 * 根据分类查询规格参数模板
	 * @param itemCatId
	 * @return
	 */
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getImtemParamByCatId(@PathVariable long itemCatId){
		TaotaoResult result = itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	/***
	 * 查询规格参数列表
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemParamForPage(int page,int rows){
		EUDataGridResult result = itemParamService.getItemParamForPage(page, rows);
		return result;
	}
	
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult saveItemParam(@PathVariable Long cid ,String paramData){
		TaotaoResult result =  itemParamService.saveItemParam(cid,paramData);
		return result;
	}
	
}
