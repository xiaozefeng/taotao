package com.taotao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbOrderItemMapper;
import com.taotao.mapper.TbOrderMapper;
import com.taotao.mapper.TbOrderShippingMapper;
import com.taotao.order.dao.JedisClient;
import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_REDIS_GEN_KEY}")
	private String ORDER_REDIS_GEN_KEY;
	
	@Value("${ORDER_ITEM_GEN_KEY}")
	private String ORDER_ITEM_GEN_KEY;
	
	@Value("${ORDER_INIT_NUM}")
	private String ORDER_INIT_NUM;
	
	@Autowired
	private TbOrderMapper tbOrderMapper;
	@Autowired
	private TbOrderItemMapper tbOrderItemMapper;
	@Autowired
	private TbOrderShippingMapper tbOrderShippingMapper;
	
	
	@Override
	public TaotaoResult createOrder(Order order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping) {
		//补全订单pojo
		String str = jedisClient.get(ORDER_REDIS_GEN_KEY);
		if(null == str){
			jedisClient.set(ORDER_REDIS_GEN_KEY, ORDER_INIT_NUM);
		}
		//订单主键策略，采用redis生成的主键
		long orderId = jedisClient.incr(ORDER_REDIS_GEN_KEY);
		Date now = new Date();
		order.setOrderId(orderId+"");
		order.setCreateTime(now);
		order.setUpdateTime(now);
		//状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
		order.setStatus(1);
		//0:未评价 1：已评价
		order.setBuyerRate(0);
		//插入数据库
		tbOrderMapper.insert(order);
		//遍历订单详情
		for (TbOrderItem orderDetail : orderItems) {
			//订单详情id
			long orderItemId = jedisClient.incr(ORDER_ITEM_GEN_KEY);
			orderDetail.setId(orderItemId+"");
			orderDetail.setOrderId(orderId+"");
			tbOrderItemMapper.insert(orderDetail);
		}
		
		//保存送货信息
		orderShipping.setOrderId(orderId+"");
		orderShipping.setCreated(now);
		tbOrderShippingMapper.insert(orderShipping);
		
		return TaotaoResult.ok(orderId);
	}

}
