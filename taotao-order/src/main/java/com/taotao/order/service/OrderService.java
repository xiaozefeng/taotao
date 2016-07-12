package com.taotao.order.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.Order;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

public interface OrderService {
	/***
	 * 创建订单
	 * @param order
	 * @param orderItems
	 * @param orderShipping
	 * @return
	 */
	TaotaoResult createOrder(Order order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping);
	
}
