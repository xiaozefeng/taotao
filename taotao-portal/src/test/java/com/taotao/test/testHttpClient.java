package com.taotao.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class testHttpClient {
	/***
	 * httpclient  GET请求
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void doGet() throws ClientProtocolException, IOException{
		//创建httpclient
		CloseableHttpClient client = HttpClients.createDefault();
		//创建get对象
		HttpGet get = new HttpGet("http://www.sogou.com");
		//执行请求
		CloseableHttpResponse response = client.execute(get);
		//获取响应结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity,"utf-8");
		System.out.println(string);
		//关闭httpclient
		response.close();
		client.close();
	}
	/***
	 * htppclient  带参数的GET请求
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@Test
	public void doGetAddParam() throws URISyntaxException, ClientProtocolException, IOException{
		CloseableHttpClient clinet = HttpClients.createDefault();
		URIBuilder buildr = new URIBuilder("http://www.sogou.com/web");
		buildr.addParameter("query", "花千骨");
		HttpGet get = new HttpGet(buildr.build());
		CloseableHttpResponse response = clinet.execute(get);
		System.out.println(response.getStatusLine().getStatusCode());
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity,"utf-8");
		System.out.println(string);
		response.close();
		clinet.close();
	}
	
	
	@Test
	public void doPost() throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:8082/test.html");
		CloseableHttpResponse response = client.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		client.close();
	}
	
	@Test
	public void doPostAddParam() throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:8082/testPostParam.html");
		List<NameValuePair> kvList = new ArrayList<>();
		kvList.add(new BasicNameValuePair("username", "zhangsan"));
		kvList.add(new BasicNameValuePair("password", "123456"));
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(kvList,"utf-8");
		post.setEntity(entity);
		CloseableHttpResponse response = client.execute(post);
		String string = EntityUtils.toString(response.getEntity());
		System.out.println(string);
		response.close();
		client.close();
	}
	
	
	
}
