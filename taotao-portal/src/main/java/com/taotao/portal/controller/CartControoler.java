package com.taotao.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartControoler {
	@Autowired
	private CartService cartService;

	@RequestMapping("/add/{itemId}")
	public String addItemToCookie(@PathVariable Long itemId, @RequestParam(value="num",defaultValue="1")Integer num, HttpServletRequest request,
			HttpServletResponse response,Model model) {
		TaotaoResult taotaoResult = cartService.addCartItem(request,response,itemId,num);
		CartItem item = (CartItem) taotaoResult.getData();
		model.addAttribute("item", item);
		return "cartSuccess";
	}
}
