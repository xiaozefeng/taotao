 package com.taotao.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.aspectj.util.FileUtil;
import org.junit.Test;

import com.taotao.common.utils.FtpUtil;

public class TestFtp {
	@Test
	public void test1() throws Exception{
		//创建ftp对象
		FTPClient ftp = new FTPClient();
		//建立连接，登录
		ftp.connect("192.168.143.129",21);
		ftp.login("ftpuser", "ftpuser");
		//上传文件
		//创建文件流
		InputStream inputStream = new FileInputStream(new File("/Users/xiaozefeng/zfxiao/picture/hello.jpg"));
		//设置上传文件的路径
		boolean change = ftp.changeWorkingDirectory("/datas/www/images");
		System.out.println(change);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		boolean storeFile = ftp.storeFile("hello1.jpg", inputStream);
		if(storeFile){
			System.out.println("上传成功");
		}
		//关闭连接
		ftp.logout();
	}
	
	
	@Test
	public void testFtpUtil() throws Exception{
		InputStream inputStream = new FileInputStream(new File("/Users/xiaozefeng/zfxiao/picture/hello.jpg"));
		FtpUtil.uploadFile("192.168.56.101", 21, "ftpuser", "ftpuser", "/data0/zfxiao/www/images", "/2016/06/12", "hello1.jpg",inputStream);
		
	}
	
}
