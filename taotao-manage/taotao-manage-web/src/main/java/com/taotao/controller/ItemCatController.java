package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCatService;
/**
 * 商品分类
 * <p>Title: ItemCatController</p>
 * <p>Description: </p>
 * <p>Company: www.ewaytec.cn</p> 
 * @author	肖泽锋
 * @date	2016年4月10日下午1:45:09
 * @version 1.0
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping("/list")
	public @ResponseBody List<EUTreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EUTreeNode> itemCatList = itemCatService.getItemCatList(parentId);
		return itemCatList;
	}
}
