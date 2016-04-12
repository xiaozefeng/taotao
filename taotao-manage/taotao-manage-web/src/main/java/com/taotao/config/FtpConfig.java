package com.taotao.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

import com.taotao.common.utils.FtpHelper;

/**
 * 
 * <p>Title: FtpConfig</p>
 * <p>Description: </p>
 * <p>Company: www.ewaytec.cn</p> 
 * @author	肖泽锋
 * @date	2016年4月12日下午3:15:18
 * @version 1.0
 */
public class FtpConfig {
	/**ftp服务器地址或者名称*/
	private String hostName;
	/**fpt服务端口号*/
	private int port;
	/**fpt服务用户名*/
	private String userName;
	/**fpt服务密码*/
	private String password;
	/**ftp下载到本地路径*/
	private String fptDownloadDir;
	/**ftpServer操作系统*/
	private String system;
	/**ftp服务端编码*/
	private String serverlanguagecode;
	/**ftp多线程下载数量*/
	private int threadNUM;

	public FtpConfig(){
		loadConfig();
	}
	
	public static final String  _URL = "/resource/resource.properties";
	
	Logger log = Logger.getLogger(FtpConfig.class);
	
	private static FtpConfig config = new FtpConfig();
	
	private void loadConfig(){
		//1、根据配置文件路径创建输入流
		InputStream inputStream = this.getClass().getResourceAsStream(_URL);
		//2、创建properties对象，读取输入流
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			log.error("ftp读取配置文件失败，请检查配置文件路径是否正确");
			e.printStackTrace();
		}
		//3、给属性赋值
		hostName = prop.getProperty("ftphostName");
		port = Integer.parseInt(prop.getProperty("port"));
		userName = prop.getProperty("userName");
		password =prop.getProperty("password");
		serverlanguagecode =prop.getProperty("serverlanguagecode");
		system =prop.getProperty("system");
		threadNUM =Integer.parseInt(prop.getProperty("threadNUM"));
		fptDownloadDir = prop.getProperty("ftpDownLoadDir");
		//4、打印日志
		log.info("配置文件信息......");
		log.info("hostName:"+hostName);
		log.info("port:"+port);
		log.info("username:"+userName);
		log.info("password:"+password);
		log.info("serverlanguagecode:"+serverlanguagecode);
		log.info("system:"+system);
		log.info("threadNUM:"+threadNUM);
		log.info("ftpDownLoadDir:"+fptDownloadDir);
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		config.hostName = hostName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		config.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		config.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		config.password = password;
	}
	public String getFptDownloadDir() {
		return fptDownloadDir;
	}
	public void setFptDownloadDir(String fptDownloadDir) {
		config.fptDownloadDir = fptDownloadDir;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		config.system = system;
	}
	public String getServerlanguagecode() {
		return serverlanguagecode;
	}
	public void setServerlanguagecode(String serverlanguagecode) {
		config.serverlanguagecode = serverlanguagecode;
	}
	public int getThreadNUM() {
		return threadNUM;
	}
	public void setThreadNUM(int threadNUM) {
		config.threadNUM = threadNUM;
	}
	public static void main(String[] args) {
		FtpHelper ftp = new FtpHelper();
		FtpConfig cof = new FtpConfig();
		ftp.connect(cof.getHostName(), cof.getPort(), cof.getUserName(), cof.getPassword());
		ftp.changeDir("www/images/2016/04/04");
		/*ftp.changeDir("images");
		ftp.changeDir("2016");
		ftp.changeDir("04");
		ftp.changeDir("04");*/
		FTPFile[] fileList = ftp.getFileList();
		for (int i = 0; i < fileList.length; i++) {
			System.out.println(fileList[i].getName());
			ftp.downLoadOneFile(fileList[i].getName(), "C:/Users/Administrator/Desktop/"+fileList[i].getName());
		}
	}
}
