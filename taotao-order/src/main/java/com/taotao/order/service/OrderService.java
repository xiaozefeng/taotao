package com.taotao.order.service;

import java.util.Date;
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
	/**
	 * 根据订单单号查询订单详情
	 * @param orderId
	 * @return
	 */
	TaotaoResult getOrderByOrderId(String orderId);
	/**
	 * 修改订单状态
	 * @param orderId
	 * @param paymentTime
	 * @param status
	 * @return
	 */
	TaotaoResult changeOrderStatus(String orderId, Date paymentTime, Integer status);
	
}
