package com.taotao.test.jedis;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.SysOauthToken;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestJedis {

	@Test
	public void testJedis1(){
		//创建jedis对象
//		Jedis jedis = new Jedis("192.168.101.185",6379);
		Jedis jedis = new Jedis("192.168.143.133",6379);
		//设置
//		jedis.set("key1", "hehe");
		String string = jedis.get("abc");
		System.out.println(string);
		//关闭jedis对象
		jedis.close();
	}
	
	/**
	 * 使用连接池
	 * 
	 */
	@Test
	public void testJedisPool(){
		//创建jedis连接池
		JedisPool pool = new JedisPool("192.168.101.183", 6379);
		//获取jedis对象
		Jedis jedis = pool.getResource();
		//jedis.set("a", "10");
		String string = "test:api:accesstoken:486f95703dab481976a9b7d1a8dcc42d";
		String str = jedis.get(string);
		System.out.println(jedis.ttl(string));
		jedis.del(string);
		System.out.println(str);
		
		jedis.close();
		pool.close();
		
	}
	
	
	/**
	 * 测试redis集群
	 * 
	 */
	@Test
	public void testRedisCluster(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.143.133", 7001));
		nodes.add(new HostAndPort("192.168.143.133", 7002));
		nodes.add(new HostAndPort("192.168.143.133", 7003));
		nodes.add(new HostAndPort("192.168.143.133", 7004));
		nodes.add(new HostAndPort("192.168.143.133", 7005));
		nodes.add(new HostAndPort("192.168.143.133", 7006));
		
		JedisCluster cluster = new JedisCluster(nodes );
		cluster.set("abc", "10000");
		String string = cluster.get("abc");
		System.out.println(string);
		cluster.close();
	}
	
	/***
	 * 测试jedis单机版
	 */
	@Test
	public void testJedisSingle(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) ac.getBean("redisClient");
		Jedis jedis = pool.getResource();
	//	String string = jedis.get("key1");
		//System.out.println(string);
		//jedis.hset("", "a", "20");
		//jedis.hset("baidu.cap.plugin.ouath.api:accessthoke", "12345678","zhi");
	//	jedis.hset("baidu.cap.plugin.ouath.api:accessthoke", "xiao","hahaha");
	//	jedis.expire("com.cap.plugin.ouath.api:accessthoke:12345678", 20);
		//jedis.del("baidu.cap.plugin.ouath.api:accessthoke");
		SysOauthToken token = new SysOauthToken();
		token.setAccessToken("486f95703dab481976a9b7d1a8dcc42d");
		token.setClientId("1000");
		token.setCreateDate(new Date());
		token.setUpdateDate(new Date());
		token.setDeptId(1000L);
		token.setDisabled(false);
		token.setDomainId(100);
		token.setPlatformToken("486f95703dab481976a9b7d1a8dcc42d");
		token.setRefreshToken("486f95703dab481976a9b7d1a8dcc42d");
		token.setUid("131123");
		token.setExpiresIn(new Date());
		String redisKey = "test:redis:size:access_token";
		String json = JsonUtils.objectToJson(token);
		for (int i = 0; i < 400000; i++) {
			//jedis.set(redisKey+i, json);
			//jedis.expire(redisKey+i, 100000);
			jedis.del(redisKey+i);
		}
		
		jedis.close();
		pool.close();
	}

	/***
	 * 测试集群版客户端
	 */
	@Test
	public void testClusterJedis(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster cluster = (JedisCluster) ac.getBean("redisClient");
		String string = cluster.get("a");
		System.out.println(string);
		cluster.close();
	}
}
