package com.taotao.portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

public interface CartService {
	/**
	 * 把商品加入购物车
	 * @param request
	 * @param response
	 * @param itemId
	 * @param num
	 * @return
	 */
	TaotaoResult addCartItem(HttpServletRequest request, HttpServletResponse response, Long itemId, int num);
	/**
	 * 获取购物车数据
	 * @param request
	 * @param response
	 * @return
	 */
	List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 修改商品数量
	 * @param request
	 * @param response
	 * @param itemId
	 * @param num
	 * @return
	 */
	TaotaoResult updateCartNum(HttpServletRequest request, HttpServletResponse response, Long itemId, Integer num);
	/**
	 * 删除商品信息
	 * @param request
	 * @param response
	 * @param itemId
	 * @return
	 */
	TaotaoResult deleteCart(HttpServletRequest request, HttpServletResponse response, Long itemId);

}
