package com.taotao.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.pojo.TbUser;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private CartService cartService; 
	@Autowired
	private OrderService orderService;
	/**
	 * 订单结算页面
	 * @return
	 */
	@RequestMapping("/order-cart")
	public String showOrderCart(HttpServletRequest request,HttpServletResponse response,Model model){
		List<CartItem> cartItemList = cartService.getCartItemList(request, response);
		//展示购物车
		model.addAttribute("cartList", cartItemList);
		return "order-cart";
	}
	
	
	@RequestMapping("/create")
	public String createOrder(Order order,Model model,HttpServletRequest request){
		TbUser user = (TbUser) request.getAttribute("user");
		if(user != null){
			order.setUserId(user.getId());
			order.setBuyerNick(user.getUsername());
		}
		String orderId = orderService.createOrder(order);
		model.addAttribute("orderId", orderId);
		model.addAttribute("payment", order.getPayment());
		model.addAttribute("date", new DateTime().plusDays(3).toString("yyyy-MM-dd"));
		return "success";
		
	}
}
