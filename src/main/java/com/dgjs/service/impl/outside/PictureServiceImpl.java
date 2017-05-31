package com.dgjs.service.impl.outside;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.outside.PictureDto;
import com.dgjs.model.outside.ReturnData;
import com.dgjs.service.outside.PictureService;
import com.dgjs.utils.HttpClientUtils;

//@Service
public class PictureServiceImpl implements PictureService{

	@SuppressWarnings("unchecked")
	@Override
	public ReturnData<List<PictureDto>> uploadPic(HttpServletRequest request, String imagePath) {
		String remote_url="http://localhost:8081/dgjs-static/picture/uploadPic";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		Iterator<String> iterator=multipartRequest.getFileNames();
		List<MultipartFile> list = new ArrayList<MultipartFile>();
		while(iterator.hasNext()){
			String fileName=iterator.next();
			MultipartFile file = multipartRequest.getFile(fileName);
			list.add(file);
		}
		String result;
		result = HttpClientUtils.uploadFile(remote_url, list, request.getCharacterEncoding(), imagePath);
		ReturnData<List<PictureDto>> returnData = JSON.parseObject(result, ReturnData.class);
		return returnData;
	}

}
