package com.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传service
 * @author xiaozefeng
 *
 */
public interface PictureUploadService {
	/**
	 * 文件上传
	 * @param multipartFile
	 * @return
	 */
	public Map uploadFile(MultipartFile multipartFile);
	
}
