package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.OrderService;

@Service
public class OrderServiceimpl implements OrderService{

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	
	@Value("${ORDER_CREATE_ADDRESS}")
	private String ORDER_CREATE_ADDRESS;
	@Override
	public String createOrder(Order order) {
		String json = JsonUtils.objectToJson(order);
		String result = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_ADDRESS,json);
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, Order.class);
		if(taotaoResult.getStatus() ==200){
			Object object = taotaoResult.getData();
			return object.toString();
		}
		return "";
	}

	
}
