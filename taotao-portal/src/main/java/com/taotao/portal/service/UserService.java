package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

public interface UserService {
	/**
	 * 根据token换取用户信息
	 * @param token
	 * @return
	 */
	TbUser getUserInfoByToken(String token);
	/***
	 * 获取回调url
	 * @return
	 */
	String getRedirectUrl();
}
