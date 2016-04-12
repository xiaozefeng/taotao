package com.taotao.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

/**
 * ftp帮助类
 * <p>
 * Title: FtpHelper
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.ewaytec.cn
 * </p>
 * 
 * @author 肖泽锋
 * @date 2016年4月11日下午5:15:03
 * @version 1.0
 */
public class FtpHelper {
	private static Logger log = Logger.getLogger(FtpHelper.class);

	public FTPClient ftpClient = new FTPClient();

	public boolean connect(String hostName, int port, String userName, String password) {
		log.info("ftp连接中......");
		log.info("hostname:" + hostName + "  port:" + port + "  username:" + userName + "  password:" + password);

		try {
			// 对服务器进行连接
			ftpClient.connect(hostName, port);
			// 设置传输编码
			ftpClient.setControlEncoding("UTF-8");

			// 设置客户端操作系统类型
			FTPClientConfig config = new FTPClientConfig("WINDOWS");
			// 设置服务端语言
			config.setServerLanguageCode("zh");

			// 验证是否已经连接服务器
			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				// 判断是否登录成功
				if (ftpClient.login(userName, password)) {
					log.debug("ftp连接成功了...");
					return true;
				}
				log.error("连接失败，用户名或密码错误");
				disconnect();
			}
		} catch (IOException e) {
			log.debug("连接不上ftp...", e);
		}
		return false;
	}

	/**
	 * 获取当前工作空间下的所ftp文件包括目录
	 * <p>
	 * Title: getFileList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	public FTPFile[] getFileList() {
		log.debug("进入查询ftp下所有文件方法......");
		try {
			FTPFile[] listFiles = ftpClient.listFiles();
			int num = 0;
			for (FTPFile ftpFile : listFiles) {
				if (!ftpFile.isFile()) {
					// 如果不是一个文件就继续循环
					continue;
				}
				num++;
			}
			log.info("进入查询上文件个数.." + num);
			log.info("进入查询ftp所有文件方法结束.....");
			return listFiles;
		} catch (IOException e) {
			log.error("查询文件失败.....", e);
			return null;
		}
	}

	/**
	 * 变更工作目录
	 * <p>
	 * Title: changeDir
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	public boolean changeDir(String remoteDir) {
		try {
			log.debug("变更目录为:" + remoteDir);
			ftpClient.changeWorkingDirectory(new String(remoteDir.getBytes("UTF-8"), "iso8859-1"));
			return true;
		} catch (IOException e) {
			log.error("变更工作目录失败.....", e);
			return false;
		}
	}

	/**
	 * 切换工作空间到父目录
	 * <p>
	 * Title: changeToParentDir
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @return
	 */
	public boolean changeToParentDir() {
		try {
			log.debug("变更工作空间到父目录.....");
			return ftpClient.changeToParentDirectory();
		} catch (IOException e) {
			log.error("变更工作空间到父目录失败....", e);
			return false;
		}
	}

	public boolean downLoadOneFile(String remote, String local) {
		log.debug(ftpClient.isConnected() ? "已经连接服务" : "尚未连接服务器");
		log.debug("开始文件下载");
		log.debug("远程文件：" + remote + "  本地存放路径：" + local);

		// 设置被动模式
		ftpClient.enterLocalActiveMode();
		try {
			// 设置以二进制方式传输文件
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		} catch (IOException e) {
			log.error("设置以二进制方式传输失败", e);
		}
		// 检测ftp服务器是否存在文件
		FTPFile[] files = null;
		try {
			files = ftpClient.listFiles(new String(remote.getBytes("UTF-8"), "iso8859-1"));
			log.debug(files == null ? "存在" : "不存在");
			log.debug("搜索出的文件名为:");
			if (files == null || files.length == 0) {
				log.debug("远程不存在文件");
				return false;
			} else {
				for (FTPFile ftpFile : files) {
					log.debug(ftpFile.getName());
				}
			}
		} catch (IOException e) {
			log.error("检查远程是否存在文件失败", e);
		}

		long ftpFileSize = files[0].getSize();
		log.debug("远程文件大小为:" + ftpFileSize);
		File localFile = new File(local);
		InputStream is =null;
		OutputStream os =null;
		try {
			// 根据文件名得到输入流
			is= ftpClient.retrieveFileStream(new String(remote.getBytes("UTF-8"), "iso8859-1"));
			// 建立输出流，设置成续传
		    os = new FileOutputStream(localFile, true);
			byte[] bytes = new byte[1024];
			// 判断本地文件是否存在，判断是否需要断点续传
			if (localFile.exists()) {
				log.info("本地文件存在，判断是否需要断点续传");
				long localFileSize = localFile.length();
				if (localFileSize >= ftpFileSize) {
					log.debug("本地文件大于远程文件，不需要做断点续传");
					return true;
				}

				log.debug("开始断点续传.....");
				ftpClient.setRestartOffset(localFileSize);

				// 已经下载的大小
				long downlandSize = localFileSize;
				int flag = 0;
				long count = 0;
				if ((ftpFileSize - downlandSize) % bytes.length == 0) {
					count = ftpFileSize - downlandSize / bytes.length;
				} else {
					count = ftpFileSize - downlandSize / bytes.length + 1;
				}

				while (true) {

					int num = is.read(bytes);
					if (num == -1) {
						break;
					}
					os.write(bytes, 0, num);
					downlandSize += num;
					flag++;
					// 打印下载进度
					if (flag % 1000 == 0) {
						log.info("下载进度为:" + downlandSize * 100 / ftpFileSize + "%");
					}
				}

			} else {
				log.debug("本地文件不存在，此文件为新文件，开始下载.....");
				//设置开始下载大小
				long downlandSize =0;
				int flag = 0;
				long count = 0;
				if ((ftpFileSize - downlandSize) % bytes.length == 0) {
					count = ftpFileSize - downlandSize / bytes.length;
				} else {
					count = ftpFileSize - downlandSize / bytes.length + 1;
				}

				while (true) {

					int num = is.read(bytes);
					if (num == -1) {
						break;
					}
					os.write(bytes, 0, num);
					downlandSize += num;
					flag++;
					// 打印下载进度
					if (flag % 1000 == 0) {
						log.info("下载进度为:" + downlandSize * 100 / ftpFileSize + "%");
					}
				}

			}
			log.debug("文件下载完成.....");
		} catch (IOException e) {
			log.error("出现io异常，请检查网络....", e);
		}finally{
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				log.error("出现io异常",e);
			}
		}
		return false;
	}

	/**
	 * 取消连接
	 * <p>
	 * Title: disconnect
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 */
	private void disconnect() {
		log.debug("进入ftp关闭连接方法...");
		// 判断是否连接到ftp
		if (ftpClient.isConnected()) {
			// 如果连接上了，就关闭连接
			try {
				log.debug("进入ftp连接关闭......");
				ftpClient.disconnect();
			} catch (IOException e) {
				log.error("关闭ftp连接出现异常");
			}
		}
	}
}
