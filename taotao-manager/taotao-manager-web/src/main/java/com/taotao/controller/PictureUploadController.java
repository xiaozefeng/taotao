package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureUploadService;

/**
 * 文件上传controller
 * @author xiaozefeng
 *
 */
@Controller
public class PictureUploadController {

	@Autowired
	private PictureUploadService pictureUploadService;
	
	@RequestMapping("/pic/upload") 
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile){
		Map result = pictureUploadService.uploadFile(uploadFile);
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}
