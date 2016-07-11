package com.taotao.portal.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_ITEM_INFO}")
	private String REST_ITEM_INFO;

	@Override
	public TaotaoResult addCartItem(HttpServletRequest request, HttpServletResponse response, Long itemId, int num) {
		CartItem result = new CartItem();
		//从cookie中取出商品列表信息
		List<CartItem> cartList = getCarItemfromCookie(request,response,itemId);
		if(cartList.size()>0){
			for (CartItem cItem : cartList) {
				if(cItem.getId() == itemId){
					//有数据 在原来的数量上 + num
					cItem.setNum(cItem.getNum()+num);
					break;
				}
			}
		}else{
			//没有数据 将商品信息加入cookie
			//获取商品信息
			String json = HttpClientUtil.doGet(REST_BASE_URL+REST_ITEM_INFO+itemId);
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItem.class);
			if(taotaoResult.getStatus() ==200){
				TbItem tbItem = (TbItem) taotaoResult.getData();
				result.setId(tbItem.getId());
				result.setNum(num);
				result.setPrice(tbItem.getPrice());
				result.setTitle(tbItem.getTitle());
				result.setImage(tbItem.getImage()==null?null:tbItem.getImage().split(",")[0]);
			}
			cartList.add(result);
		}
		
		//将购物车重新加入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartList),true);
		return TaotaoResult.ok(result);
	}

	private List<CartItem> getCarItemfromCookie(HttpServletRequest request, HttpServletResponse response, Long itemId) {
		List<CartItem> result = new ArrayList<>();
		String json = CookieUtils.getCookieValue(request, "TT_CART", true);
		if(null == json){
			return result;
		}
		try {
			List<CartItem> jsonList = JsonUtils.jsonToList(json, CartItem.class);
			return  jsonList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

}
