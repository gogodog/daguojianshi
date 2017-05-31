package com.dgjs.service.outside;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dgjs.model.outside.ReturnData;

public interface PictureService {

	public ReturnData<List<com.dgjs.model.outside.PictureDto>> uploadPic(HttpServletRequest request,String imagePath);

}
