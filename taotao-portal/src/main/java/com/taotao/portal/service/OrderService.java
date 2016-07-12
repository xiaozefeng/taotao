package com.taotao.portal.service;

import com.taotao.portal.pojo.Order;

public interface OrderService {
	/**
	 * 创建订单
	 * @param order
	 * @return
	 */
	String createOrder(Order order);
}
