package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.annotation.CSRF;
import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;

/**
 * 上传图片处理
 * <p>Title: PictureController</p>
 * <p>Description: </p>
 * <p>Company: www.ewaytec.cn</p> 
 * @author	肖泽锋
 * @date	2016年4月4日下午2:27:30
 * @version 1.0
 */
@Controller
public class PictureController {
	@Autowired
	private PictureService pictureService;
	@CSRF
	@RequestMapping("/pic/upload")
	public @ResponseBody PictureResult pictureUpload(MultipartFile uploadFile){
		return pictureService.uploadPicture(uploadFile);
	}
}
