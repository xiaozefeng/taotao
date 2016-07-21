package com.taotao.order.service.impl;

import java.util.ArrayList;
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
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderItemExample;
import com.taotao.pojo.TbOrderItemExample.Criteria;
import com.taotao.pojo.TbOrderShipping;
import com.taotao.pojo.TbOrderShippingExample;

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


	@Override
	public TaotaoResult getOrderByOrderId(String orderId) {
		Order result = new Order();
		//查询订单信息
		TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(orderId);
		result.setOrderId(tbOrder.getOrderId());
		result.setPayment(tbOrder.getPayment());
		result.setPaymentType(tbOrder.getPaymentType());
		result.setPostFee(tbOrder.getPostFee());
		result.setStatus(tbOrder.getStatus());
		result.setUserId(tbOrder.getUserId());
		result.setCreateTime(tbOrder.getCreateTime());
		result.setBuyerMessage(tbOrder.getBuyerMessage());
		result.setBuyerNick(tbOrder.getBuyerNick());
		
		//查询订单详情
		TbOrderItemExample example1 =new TbOrderItemExample();
		List<TbOrderItem> orderItemList = selectOrderItemByOrderId(orderId, example1);
		result.setOrderItems(orderItemList);
		//查询收货信息
		List<TbOrderShipping> orderShipiingList = selectOrderShipping(orderId);
	    if(orderShipiingList.size() >0){
	    	result.setOrderShipping(orderShipiingList.get(0));
	    }
	    
		return TaotaoResult.ok(result);
	}

	/**
	 * 根据订单编号查询商品详情信息
	 * @param orderId
	 * @param example1
	 * @return
	 */
	private List<TbOrderItem> selectOrderItemByOrderId(String orderId, TbOrderItemExample example1) {
		Criteria criteria = example1.createCriteria();
		 criteria.andOrderIdEqualTo(orderId);
		 List<TbOrderItem> orderItemList = new ArrayList<>();
	     orderItemList = tbOrderItemMapper.selectByExample(example1);
		return orderItemList;
	}

	/***
	 * 根据订单编号查询收货信息
	 * @param orderId
	 * @return
	 */
	private List<TbOrderShipping> selectOrderShipping(String orderId) {
		TbOrderShippingExample example2 = new TbOrderShippingExample();
		com.taotao.pojo.TbOrderShippingExample.Criteria criteria2 = example2.createCriteria();
		criteria2.andOrderIdEqualTo(orderId);
		List<TbOrderShipping>  orderShipiingList = tbOrderShippingMapper.selectByExample(example2);
		return orderShipiingList;
	}


	@Override
	public TaotaoResult changeOrderStatus(String orderId, Date paymentTime, Integer status) {
		//查询订单信息
		TbOrder order = tbOrderMapper.selectByPrimaryKey(orderId);
		if(order == null){
			return TaotaoResult.build(400, "此订单不存在");
		}
		order.setPaymentTime(paymentTime);
		order.setStatus(status);
		tbOrderMapper.updateByPrimaryKey(order);
		//修改订单信息
		return TaotaoResult.ok();
	}

}
