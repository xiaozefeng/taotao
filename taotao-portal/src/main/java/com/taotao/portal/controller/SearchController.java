package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/***
 * 搜索服务Controller
 * @author xiaozefeng
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String search(@RequestParam("q")String queryString,@RequestParam(value="page",defaultValue="1")Integer page,Model model){
		if(StringUtils.isNotBlank(queryString)){
			try {
				queryString = new String(queryString.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		SearchResult search = searchService.search(queryString, page);
		model.addAttribute("query",queryString);
		model.addAttribute("totalPage",search.getRecordCount());
		model.addAttribute("itemList", search.getItemList());
		model.addAttribute("page", page);
		return "search";
		
	}
}
