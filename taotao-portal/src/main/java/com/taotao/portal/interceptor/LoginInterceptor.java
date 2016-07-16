package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.impl.UserServiceImpl;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userServiceImpl;

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从cookie中拿到token信息
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		// 通过token换取用户信息
		TbUser user = userServiceImpl.getUserInfoByToken(token);
		// 判断用户是否登录
		if (null == user) {
			// 否 跳转到登录页面
			response.sendRedirect(userServiceImpl.SSO_URL + userServiceImpl.USER_SHOWLOGIN + "?redirect="
					+ request.getRequestURL());
			return false;
		}
		request.setAttribute("user", user);
		// 是 跳转到对应地址
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 已经返回modelandview了，只能拿到异常信息
	}

}
