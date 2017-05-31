package com.dgjs.service.common;


import javax.servlet.http.HttpServletRequest;

import com.dgjs.model.dto.PictureDto;

public interface PictureService {

	public PictureDto uploadPic(HttpServletRequest request,String imagePath,String fileName);
	
	public String getImageContextPath();
	
}
