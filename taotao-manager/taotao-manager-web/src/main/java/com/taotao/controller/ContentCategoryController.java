package com.taotao.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 类容分类
 * @author xiaozefeng
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentCategoryService;
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/***
	 * 获取内容分类树
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") Long parentId){
		return contentCategoryService.getContentCategoryList(parentId);
	}
	
	/**
	 * 新增内容分类节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createContentCategory(Long parentId,String name){
		TaotaoResult result = contentCategoryService.createContentCategory(parentId, name);
		return result;
	}
	
	@RequestMapping("/delete/")
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long id){
		TaotaoResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(Long id,String name){
		TbContentCategory record = new TbContentCategory();
		record.setId(id);
		record.setName(name);
		return contentCategoryService.updateContentCategory(record);
	}
}
