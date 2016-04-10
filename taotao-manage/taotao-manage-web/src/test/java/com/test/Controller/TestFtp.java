package com.test.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class TestFtp {
/*	@Test
	public void ftpTest() throws Exception{
		//创建ftp  
		FTPClient ftpClient = new FTPClient();
		//建立链接
		ftpClient.connect("192.168.68.132",21);
		//登录ftp服务器
		ftpClient.login("ftpuser", "ftpuser");
		//读取本地文件
		FileInputStream fis = new FileInputStream(new File("G:/图片/aa.gif"));
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		//修改上传文件的格式
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		//上传文件
		//第一个参数的：服务器文档名
		//第二个参数：上传文档的inputStream
		ftpClient.storeFile("demo.gif",fis);
		ftpClient.logout();
		
	}
	*/
	/*@Test
	public void testFtpUtil()throws Exception{
		FileInputStream fis = new FileInputStream(new File("g:/图片/aaa.jpg"));
		FtpUtil.uploadFile("192.168.68.132", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images", "2016/04/04", "hello2.jpg", fis);
	}*/
}
