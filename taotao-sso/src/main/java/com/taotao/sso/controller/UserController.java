package com.taotao.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
/***
 * 用户接口controller
 * @author xiaozefeng
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	/***
	 * 打开注册页面
	 * @return
	 */
	@RequestMapping("/showRegister")
	public String showRegister(){
		return "register";
	}
	
	/**
	 *跳转登录页面 
	 * @return
	 */
	@RequestMapping("/showLogin")
	public String showLogin(String redirect ,Model model){
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	/**
	 * 用户参数信息检查接口
	 * @param param
	 * @param type
	 * @param callback
	 * @return
	 */
	@RequestMapping(value="/check/{param}/{type}",method=RequestMethod.GET)
	@ResponseBody
	public Object checkData(@PathVariable String param,@PathVariable Integer type,String callback){
		TaotaoResult result = null;
		//由于restful风格的url参数不能为空，否则报404错误，所以不做空校验
		if(type != 1&& type != 2&& type != 3){
			result = TaotaoResult.build(400, "type参数格式不正确");
		}
		if(result != null){
			if(StringUtils.isNoneBlank(callback)){
				MappingJacksonValue view = new MappingJacksonValue(result);
				view.setJsonpFunction(callback);
				return view;
			}
			return result;
		}
		
		try {
			result = userService.checkData(param, type);
		} catch (Exception e) {
			e.printStackTrace();
			result= TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		
		if(callback != null){
			MappingJacksonValue view = new MappingJacksonValue(result);
			view.setJsonpFunction(callback);
			return view;
		}
		
		return result;
	}
	
	/***
	 * 用户注册接口
	 * @param user
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userRegister(TbUser user){
		try {
			return userService.userRegister(user);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	/***
	 * 用户登录接口
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult userLogin(String username,String password,HttpServletRequest request,
			HttpServletResponse response){
		if(StringUtils.isBlank(username)){
			return TaotaoResult.build(400, "用户名不能为空");
		}
		if(StringUtils.isBlank(password)){
			return TaotaoResult.build(400, "密码不能为空");
		}
		try {
			return userService.userLogin(username,password,request,response);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	/***
	 * 根据token查询用户信息
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping(value="/token/{token}")
	@ResponseBody
	public Object getUserInfoByToken(@PathVariable String token,String callback){
		TaotaoResult taotaoResult = null;
		try {
			taotaoResult = userService.getUserInfoByToken(token);
		} catch (Exception e) {
			e.printStackTrace();
			taotaoResult = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		if(callback != null){
			MappingJacksonValue view = new MappingJacksonValue(taotaoResult);
			view.setJsonpFunction(callback);
			return view;
		}
		return taotaoResult;
	}
	
	@RequestMapping(value="/logout/{token}",method=RequestMethod.GET)
	@ResponseBody
	public Object logout(@PathVariable String token,String callback){
		TaotaoResult taotaoResult = null;
		try {
			taotaoResult = userService.userLogout(token);
		} catch (Exception e) {
			e.printStackTrace();
			taotaoResult = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		if(callback != null){
			MappingJacksonValue value = new MappingJacksonValue(taotaoResult);
			value.setJsonpFunction(callback);
			return value;
		}
		return taotaoResult;
	}
}
