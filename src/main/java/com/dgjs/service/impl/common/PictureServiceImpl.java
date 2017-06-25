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
	
	private static String zipPath="/p_";//压缩图存放位置
	
	private static String tailor="/t_h_";//裁剪高度为200的图片

	@Override
	public String getImageContextPath() {
		return imageContextPath;
	}
	
	@Override
	public PictureDto uploadPic(HttpServletRequest request,String imagePath,String fileName) {
		return uploadProcessedPic(request,imagePath,fileName,1f,null,null);
	}
	
	public PictureDto uploadPic(HttpServletRequest request,String imagePath,String fileName,int height,int width){
		return uploadProcessedPic(request,imagePath,fileName,1f,height,width);
	}
	
	@SuppressWarnings("unused")
	public PictureDto uploadProcessedPic(HttpServletRequest request,String imagePath,String fileName,Float scale,Integer height,Integer width){
		PictureDto dto=new PictureDto();
		try {
	    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
	    	MultipartFile file  =  multipartRequest.getFile(fileName);
	        InputStream inputStream= file.getInputStream();
	        if(file.getSize()==0||inputStream==null||StringUtils.isEmpty(imagePath)){
	        	dto.setErrorInfo(RETURN_STATUS.PARAM_ERROR.getValue(), "请传入图片");
	        }else{
	 	        String imageName=PictureUtils.generateImageName();
	 	        String saveImagePath=PictureUtils.getImageSavePath(saveRealBasePath,imagePath,imageName);
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
	 	        String imageUrl=PictureUtils.getImageAccessPath(webBasePath,imagePath, imageName);
	 	        dto.setImageUrl(imageUrl);
	 	        if(height!=null && width!=null){
	 	        	 String tailorImageUrl= getTailorImageUrl(imagePath,fileName,imageName,saveImagePath,scale,height,width);
	 	        	 tailorImageUrl= webBasePath+tailorImageUrl.replaceAll(saveRealBasePath, "");
	 	        	 dto.setTailorImageUrl(tailorImageUrl);
	 	        }else if(scale!=null){
	 	        	 String minImageUrl=getMinImageUrl(imagePath,fileName,imageName,saveImagePath,scale);//获取压缩图片路径
	 	        	 minImageUrl= webBasePath+minImageUrl.replaceAll(saveRealBasePath, "");
	 	        	 dto.setMinImageUrl(minImageUrl);
	 	        }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        dto.setErrorInfo(RETURN_STATUS.SYSTEM_ERROR.getValue(), e.getMessage());
	    }
		return dto;
	}
	
	
	private String getMinImageUrl(String imagePath,String fileName,String imageName,String saveImagePath,float scale) throws IOException{
		String p1ImagePath=PictureUtils.getImageSavePath(saveRealBasePath,imagePath+zipPath+(int)scale*100,imageName);//1:1压缩图存放位置
		String minImageUrl = PictureUtils.thumbnailatorImage(saveImagePath, p1ImagePath, scale);
		return minImageUrl;
	}
	
	private String getTailorImageUrl(String imagePath,String fileName,String imageName,String saveImagePath,Float scale,int height,int width) throws IOException{
	    String tailorImagePath=PictureUtils.getImageSavePath(saveRealBasePath,imagePath+tailor+height,imageName);//裁剪图片位置
		String tailorImageUrl = PictureUtils.thumbnailatorImage(saveImagePath, tailorImagePath, height, width);//后裁剪
		return tailorImageUrl;
	}
}
