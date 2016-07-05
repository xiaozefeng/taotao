package com.taotao.test.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class TestJedis {

	@Test
	public void testJedis1(){
		//创建jedis对象
		Jedis jedis = new Jedis("192.168.56.101",6379);
		//设置
		jedis.set("key1", "hehe");
		String string = jedis.get("key1");
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
		JedisPool pool = new JedisPool("192.168.56.101", 6379);
		//获取jedis对象
		Jedis jedis = pool.getResource();
		//jedis.set("a", "10");
		String str = jedis.get("key1");
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
		nodes.add(new HostAndPort("192.168.56.101", 7001));
		nodes.add(new HostAndPort("192.168.56.101", 7002));
		nodes.add(new HostAndPort("192.168.56.101", 7003));
		nodes.add(new HostAndPort("192.168.56.101", 7004));
		nodes.add(new HostAndPort("192.168.56.101", 7005));
		nodes.add(new HostAndPort("192.168.56.101", 7006));
		
		JedisCluster cluster = new JedisCluster(nodes );
		cluster.set("abc", "10000");
		String string = cluster.get("a");
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
