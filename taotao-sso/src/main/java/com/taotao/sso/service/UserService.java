package com.taotao.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

public interface UserService {
	/**
	 * 用户参数信息检查接口
	 * @param data
	 * @param type
	 * @return
	 */
	TaotaoResult checkData(String data,Integer type);
	/***
	 * 用户注册
	 * @param user
	 * @return
	 */
	TaotaoResult userRegister(TbUser user);
	/**
	 * 用户登录接口
	 * @param username
	 * @param password
	 * @return
	 */
	TaotaoResult userLogin(String username, String password,HttpServletRequest request,
			HttpServletResponse response);
	/**
	 * 根据token获取用户信息接口
	 * @param token
	 * @return
	 */
	TaotaoResult getUserInfoByToken(String token);
	/**
	 * 安全退出接口
	 * @param token
	 * @return
	 */
	TaotaoResult userLogout(String token);
}
