package com.taotao.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger log =  LoggerFactory.getLogger(getClass());
	@Override
	public String createOrder(Order order) {
		String json = JsonUtils.objectToJson(order);
		log.debug("订单信息:"+json);
		log.debug("接口地址"+ORDER_BASE_URL);
		log.debug("接口地址"+ORDER_CREATE_ADDRESS);
		String result = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_ADDRESS,json);
		log.debug("调用结果"+result);
		TaotaoResult taotaoResult = TaotaoResult.format(result);
		if(taotaoResult.getStatus() ==200){
			Object object = taotaoResult.getData();
			return object.toString();
		}
		return "";
	}

	
}
