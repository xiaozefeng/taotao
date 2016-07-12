package com.taotao.portal.controller;

import java.util.List;

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

	/***
	 * 将商品加入购物车
	 * 
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/add/{itemId}")
	public String addItemToCookie(@PathVariable Long itemId,
			@RequestParam(value = "num", defaultValue = "1") Integer num, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		TaotaoResult taotaoResult = cartService.addCartItem(request, response, itemId, num);
		CartItem item = (CartItem) taotaoResult.getData();
		model.addAttribute("item", item);
		return "redirect:/cart/success.html";
	}

	@RequestMapping("/success")
	public String toSuccess() {
		return "cartSuccess";
	}
 
	/**
	 * 获取购物车所有商品信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/cart")
	public String getCartList(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<CartItem> cartList = cartService.getCartItemList(request, response);
		model.addAttribute("cartList", cartList);
		return "cart";
	}

	/**
	 * 修改购物车商品数量
	 * 
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/update/{itemId}/{num}")
	public String UpdateCart(@PathVariable Long itemId, @PathVariable Integer num, HttpServletRequest request,
			HttpServletResponse response) {
		if (num == 0) {
			throw new RuntimeException();
		}
		cartService.updateCartNum(request, response, itemId, num);
		return "redirect:/cart/cart.html";
	}
	/**
	 * 从购物车删除商品信息
	 * @param itemId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
		cartService.deleteCart(request,response,itemId);
		return "redirect:/cart/cart.html";
	}
}
