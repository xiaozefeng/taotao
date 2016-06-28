package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
/**
 * 展示规格参数
 * @author xiaozefeng
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.service.ItemParamItemService;
@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/showitem/{itemId}")
	public String showItemParam(@PathVariable Long itemId,Model model){
		String result = itemParamItemService.showItemParams(itemId);
		model.addAttribute("itemParam", result);
		return "item";
	}
}
