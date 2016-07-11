package com.taotao.portal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;

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

}
