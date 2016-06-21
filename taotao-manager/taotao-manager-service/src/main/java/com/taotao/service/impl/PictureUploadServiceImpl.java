package com.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureUploadService;

@Service
public class PictureUploadServiceImpl implements PictureUploadService {
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_PATH}")
	private String IMAGE_PATH;
	
	@Override
	public Map uploadFile(MultipartFile multipartFile) {
		Map resultMap = new HashMap<>();
		try {
			//生成文件名
			String oldName = multipartFile.getOriginalFilename();
			String newName = IDUtils.genImageName()+oldName.substring(oldName.indexOf("."));
			//上传文件
			String filePath = new DateTime().toString("/yyyy/MM/dd");
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, filePath,
					newName, multipartFile.getInputStream());
			if(!result){
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
			}else{
				resultMap.put("error", 0);
				resultMap.put("url", IMAGE_PATH+filePath+"/"+newName);
			}
		} catch (IOException e) {
			e.printStackTrace();
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传失败");
		}
		
		return resultMap;
	}

}
