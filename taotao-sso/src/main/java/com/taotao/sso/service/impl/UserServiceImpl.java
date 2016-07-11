package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.pojo.TbUserExample.Criteria;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;

	@Autowired
	private JedisClient jedisClient;

	@Value("${REDIS_SESSION_KEY}")
	private String REDIS_SESSION_KEY;

	@Value("${REDIS_SESSION_TIME}")
	private Integer REDIS_SESSION_TIME;

	@Override
	public TaotaoResult checkData(String data, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		if (1 == type) {
			criteria.andUsernameEqualTo(data);
		} else if (2 == type) {
			criteria.andPhoneEqualTo(data);
		} else if (3 == type) {
			criteria.andEmailEqualTo(data);
		}
		List<TbUser> list = userMapper.selectByExample(example);
		// 参数信息可用
		if (list != null && list.size() == 0) {
			return TaotaoResult.ok(true);
		}
		// 参数信息不可用
		return TaotaoResult.ok(false);
	}

	@Override
	public TaotaoResult userRegister(TbUser user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult userLogin(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if (list == null && list.size() == 0) {
			return TaotaoResult.build(400, "用户名或者密码错误");
		}
		TbUser user = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return TaotaoResult.build(400, "用户名或者密码错误");
		}
		// 置空密码
		user.setPassword(null);
		// 将用户信息放入缓存
		String token = UUID.randomUUID().toString();
		String key = REDIS_SESSION_KEY + ":" + token;
		jedisClient.set(key, JsonUtils.objectToJson(user));
		jedisClient.expire(key, REDIS_SESSION_TIME);

		// 登录成功后将token放入cookie中
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
		
		return TaotaoResult.ok(token);
	}

	@Override
	public TaotaoResult getUserInfoByToken(String token) {
		String key = REDIS_SESSION_KEY + ":" + token;
		String json = jedisClient.get(key);
		if (json == null) {
			return TaotaoResult.build(400, "token不存在或者session已过期");
		}
		// 刷新token过期时间
		jedisClient.expire(key, REDIS_SESSION_TIME);
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

	@Override
	public TaotaoResult userLogout(String token) {
		long result = jedisClient.del(REDIS_SESSION_KEY + ":" + token);
		if (result > 0) {
			return TaotaoResult.ok("");
		}
		return TaotaoResult.build(400, "token不存在或者已过期");
	}

}
