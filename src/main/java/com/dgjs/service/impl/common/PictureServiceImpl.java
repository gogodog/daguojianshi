package com.dgjs.service.impl.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PictureDto;
import com.dgjs.service.common.PictureService;
import com.dgjs.utils.PictureUtils;

@Service
public class PictureServiceImpl implements PictureService{
	
	@Value("${saveRealBasePath}")
	private String saveRealBasePath;
	
	@Value("${webBasePath}")
	private String webBasePath;
	
	@Value("${imageContextPath}")
	private String imageContextPath;
	
	private static String oneToOneZipPath="/p1";//1:1压缩图存放位置

	@Override
	public PictureDto uploadPic(HttpServletRequest request,String imagePath,String fileName) {
		PictureDto dto=new PictureDto();
		try {
	    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
	    	MultipartFile file  =  multipartRequest.getFile(fileName);
	        InputStream inputStream= file.getInputStream();
	        if(file.getSize()==0||inputStream==null||StringUtils.isEmpty(imagePath)){
	        	dto.setErrorInfo(RETURN_STATUS.PARAM_ERROR.getValue(), "请传入图片");
	        }else{
	 	        String imageName=PictureUtils.generateImageName();
	 	        String saveImagePath=PictureUtils.getImageSavePath(saveRealBasePath,imagePath,request,imageName);
	 	        String p1ImagePath=PictureUtils.getImageSavePath(saveRealBasePath,imagePath+oneToOneZipPath,request,imageName);//1:1压缩图存放位置
	 	        @SuppressWarnings("unused")
				int flag;
	 	        byte[] buff=new byte[1024*1024];
	 	        File outputfile=new File(saveImagePath);
	 	        outputfile.createNewFile();
	 	        FileOutputStream outputStream =new FileOutputStream(outputfile,true);
	 	        while((flag=inputStream.read(buff))!=-1){
	 	        	outputStream.write(buff);
	 	        }
	 	        inputStream.close();
	 	        outputStream.close();
	 	        String imageUrl=PictureUtils.getImageAccessPath(webBasePath,imagePath,request, imageName);
	 	        String minImageUrl = PictureUtils.thumbnailatorImage(saveImagePath, p1ImagePath, 1f);
	 	        minImageUrl= webBasePath+minImageUrl.replaceAll(saveRealBasePath, "");
	 	        dto.setMinImageUrl(minImageUrl);
	 	        dto.setImageUrl(imageUrl);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        dto.setErrorInfo(RETURN_STATUS.SYSTEM_ERROR.getValue(), e.getMessage());
	    }
		return dto;
	}

	@Override
	public String getImageContextPath() {
		// TODO Auto-generated method stub
		return imageContextPath;
	}

}
