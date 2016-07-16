package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_URL}")
	public String SSO_URL;
	
	@Value("${SSO_TOKEN_URL}")
	private String SSO_TOKEN_URL;
	
	@Value("${USER_SHOWLOGIN}")
	public String USER_SHOWLOGIN;
	
	
	
	@Override
	public TbUser getUserInfoByToken(String token) {
		//调用sso接口
		try {
			String json = HttpClientUtil.doGet(SSO_BASE_URL+SSO_TOKEN_URL+token);
			TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbUser.class);
			TbUser user = (TbUser) taotaoResult.getData();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getRedirectUrl() {
		return null;
	}

}
