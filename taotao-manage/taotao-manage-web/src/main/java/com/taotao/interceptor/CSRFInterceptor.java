package com.taotao.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.taotao.annotation.CSRF;

/**
 * 防重复提交拦截器
 * Title: CSRFInterceptor
 * Description:
 * ompany: www.ewaytec.cn
 * @author 肖泽锋
 * @date 2016年4月6日下午3:53:36
 * @version 1.0
 */
public class CSRFInterceptor extends HandlerInterceptorAdapter {
	/** 日志 */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (null != handler && handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();

			CSRF annotation = method.getAnnotation(CSRF.class);
			if (null != annotation) {
				boolean needSaveToken = annotation.needSaveToken();
				if (needSaveToken) {
					request.getSession(false).setAttribute("token", UUID.randomUUID().toString());
				}
				boolean needRomoveToken = annotation.needRomoveToken();
				if (needRomoveToken) {
					if (isRepeatSubmit(request)) {
						log.warn("please don't repeat submit,[url:" + request.getServletPath() + "]");
						return false;
					}
					request.getSession(false).removeAttribute("token");
				}
			}
			
		}
		return true;
	}

	/**
	 * 对比
	 * <p>
	 * Title: isRepeatSubmit
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param request
	 * @return
	 */
	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute("token");
		if (null == serverToken) {
			return true;
		}
		String clientToken = request.getParameter("token");
		if (null == clientToken) {
			return true;
		}
		if (!serverToken.equals(clientToken)) {
			return true;
		}
		return false;
	}
}
