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
		CartItem result = null;
		//从cookie中取出商品列表信息
		List<CartItem> cartList = getCarItemfromCookie(request,response);
			for (CartItem cItem : cartList) {
				if(cItem.getId() == itemId){
					//有数据 在原来的数量上 + num
					cItem.setNum(cItem.getNum()+num);
					result = cItem;
					break;
				}
			}
		if(result== null){
			result = new CartItem();
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
	/**
	 * 获取购物车所有商品信息
	 * @param request
	 * @param response
	 * @param itemId
	 * @return
	 */
	private List<CartItem> getCarItemfromCookie(HttpServletRequest request, HttpServletResponse response) {
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

	@Override
	public List<CartItem> getCartItemList(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> carItemfromCookie = getCarItemfromCookie(request, response);
		return carItemfromCookie;
	}
	
	@Override
	public TaotaoResult updateCartNum(HttpServletRequest request, HttpServletResponse response, Long itemId,
			Integer num) {
		CartItem result = new CartItem();
		//从cookie中取出商品列表信息
		List<CartItem> cartList = getCarItemfromCookie(request,response);
			for (CartItem cItem : cartList) {
				if(cItem.getId() == itemId){
					//有数据 在原来的数量上 + num
					cItem.setNum(num);
					break;
				}
			}
		//将购物车重新加入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(cartList),true);
		return TaotaoResult.ok(result);
	}
	@Override
	public TaotaoResult deleteCart(HttpServletRequest request, HttpServletResponse response, Long itemId) {
		List<CartItem> list = getCarItemfromCookie(request, response);
		for (CartItem cartItem : list) {
			if(cartItem.getId() == itemId){
				list.remove(cartItem);
				break;
			}
		}
		//重新将list写入cookie
		CookieUtils.setCookie(request, response, "TT_CART", JsonUtils.objectToJson(list),true);
		return TaotaoResult.ok();
	}

}
