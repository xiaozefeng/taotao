package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;

public interface PictureService {
	/**
	 * 图片上传
	 * <p>Title: uploadPicture</p>
	 * <p>Description: </p>
	 * @param multipartFile
	 * @return
	 */
	PictureResult uploadPicture(MultipartFile multipartFile);
}
