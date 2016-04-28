package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public @ResponseBody Object getItemById(@RequestParam(value = "id", required = true) Long id) {
		TbItem item = itemService.geTbItemById(id);
		return item;
	}

	@RequestMapping(value = "/item/list", method = RequestMethod.GET)
	public @ResponseBody EUDataGridResult getItemList(@RequestParam(value = "page") Integer page,
			@RequestParam(value = "rows") Integer rows) {
		return itemService.getItemList(page, rows);
	}

	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	public @ResponseBody TaotaoResult saveItem(TbItem item, @RequestParam(value = "desc") String desc)
			throws Exception {
		return itemService.addItem(item, desc);
	}

	@RequestMapping(value = "/item/test", method = RequestMethod.POST)
	public @ResponseBody TaotaoResult test(@RequestBody com.taotao.pojo.User user) {
		return TaotaoResult.ok();

	}

}
