package com.dgjs.service.common;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dgjs.model.dto.PictureDto;
import com.dgjs.model.dto.ThumbnailatorDto;

public interface PictureService {
	
	public List<PictureDto> uploadPic(HttpServletRequest request,String imagePath,String fileName,ThumbnailatorDto thumbnailator);
	
	public String getImageContextPath();
	
}
