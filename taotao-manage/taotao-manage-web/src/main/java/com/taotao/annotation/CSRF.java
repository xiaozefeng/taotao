package com.taotao.annotation;
/**
 * 防重复提交
 * <p>Title: CSRFInterceptor</p>
 * <p>Description: </p>
 * <p>Company: www.ewaytec.cn</p> 
 * @author	肖泽锋
 * @date	2016年4月6日下午3:50:45
 * @version 1.0
 */
public @interface CSRF {
	
	boolean needSaveToken() default true;
	boolean needRomoveToken() default true;
}
