package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
/**
 * 商品内容controller
 * @author xiaozefeng
 *
 */
@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EUDataGridResult getContentList(int page,int rows,Long categoryId){
		return contentService.getContentList(page, rows, categoryId);
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult saveContent(TbContent tbContent){
		TaotaoResult result = contentService.insertContent(tbContent);
		return result;
	}
}
