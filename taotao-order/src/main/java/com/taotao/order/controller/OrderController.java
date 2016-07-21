package com.taotao.order.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	/**
	 * 创建订单
	 * @param order
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createOrder(@RequestBody Order order){
		try {
			return  orderService.createOrder(order,order.getOrderItems(),order.getOrderShipping());
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	/**
	 * 查询订单信息
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value="/info/{orderId}",method=RequestMethod.GET)
	@ResponseBody
	public TaotaoResult getOrderById(@PathVariable String orderId){
		try {
			return orderService.getOrderByOrderId(orderId);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	@RequestMapping(value="/changeStatus",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult changeOrderStatus(String orderId,Long paymentTime,Integer status){
		if(StringUtils.isBlank(orderId)){
			return TaotaoResult.build(400, "订单号不能为空");
		}
		
		if(paymentTime == null){
			return TaotaoResult.build(400, "支付时间不能为空");
		}
		
		if(status == null){
			return TaotaoResult.build(400, "订单号状态不能为空");
		}
		//转换时间
		Date paymentDateTime = new DateTime(paymentTime).toDate();
		try {
			return orderService.changeOrderStatus(orderId,paymentDateTime,status);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	
	
}
