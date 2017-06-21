package com.dgjs.service.common;


import javax.servlet.http.HttpServletRequest;

import com.dgjs.model.dto.PictureDto;

public interface PictureService {

	//默认上传图片接口，会走1：1压缩
	public PictureDto uploadPic(HttpServletRequest request,String imagePath,String fileName);
	
	//上传裁剪图片
	public PictureDto uploadPic(HttpServletRequest request,String imagePath,String fileName,int height,int width);
	
	
	public String getImageContextPath();
	
}
