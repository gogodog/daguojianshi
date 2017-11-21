package com.dgjs.service.impl.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dgjs.constants.Constants;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PictureDto;
import com.dgjs.model.dto.ThumbnailatorDto;
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
	
	@Value("${fastFDSContextPath}")
	private String fastFDSContextPath;
	
	@Value("${watermarker}")
	private String watermarker;
	
	@Value("${imageContextPath}${webBasePath}")
	private String webContextPath;
	
	private static String zipPath="/p_";//压缩图存放位置
	
	private static String tailor="/t_h_";//裁剪高度为x的图片
	
	private static String wartermark="/wm_";//裁剪高度为200的图片路径

	@Override
	public String getImageContextPath() {
		return imageContextPath;
	}
	

	@Override
	public List<PictureDto> uploadPic(HttpServletRequest request,String imagePath,String fileName,ThumbnailatorDto thumbnailator) {
		List<PictureDto> list = new ArrayList<PictureDto>();
	    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
	    List<MultipartFile> files=multipartRequest.getFiles(fileName);
	    for(MultipartFile file:files){
	     	PictureDto pdto=uploadFile(file,thumbnailator,imagePath);
	     	list.add(pdto);
	    }
	    return list;
	}
	
	private PictureDto uploadFile(MultipartFile file,ThumbnailatorDto thumbnailator,String imagePath){
		 PictureDto dto=new PictureDto();
		 try{
			    InputStream inputStream= file.getInputStream();
		        if(file.getSize()==0||inputStream==null||StringUtils.isEmpty(imagePath)||!file.getContentType().equals("image/jpeg")){
		        	dto.setErrorInfo(RETURN_STATUS.PARAM_ERROR.getValue(), "请传入图片");
		        }else{
		        	dto.setOriginName(getOriginFileName(file.getOriginalFilename()));
		 	        String imageName=PictureUtils.generateImageName();
		 	        String saveImagePath=PictureUtils.getImageSavePath(saveRealBasePath,imagePath,imageName);
					int flag;
					int buffSize=1024*512;
		 	        byte[] buff=new byte[buffSize];
		 	        File outputfile=new File(saveImagePath);
		 	        outputfile.createNewFile();
		 	        FileOutputStream outputStream =new FileOutputStream(outputfile,true);
		 	        int index=0;
		 	        while((flag=inputStream.read(buff))!=-1){
		 	        	outputStream.write(buff);
		 	        	index++;
		 	        }
		 	        inputStream.close();
		 	        outputStream.close();
		 	        //已经超过了上传最大尺寸
		 	        if(Constants.MAX_FILE_SIZE * 1024 * 1024 < index * buffSize){
		 	        	dto.setErrorInfo(RETURN_STATUS.PARAM_ERROR.getValue(),file.getName()+"超过"+Constants.MAX_FILE_SIZE+"M");
		 	        	return dto;
		 	        }
		 	        if(thumbnailator.isAdapt()){
		 	        	thumbnailator.calScale(index*buffSize);
		 	        }
		 	        String imageUrl=PictureUtils.getImageAccessPath(webBasePath,imagePath, imageName);
		 	        dto.setImageUrl(imageUrl);
		 	        if(thumbnailator!=null){
		 	        	thumbnailator.setFromPath(saveImagePath);
		 	        	if(thumbnailator.getPositions()!=null){
		 	        		 thumbnailator.setWatermark(watermarker);
		 	        		 String p1ImagePath=PictureUtils.getImageSavePath(saveRealBasePath,imagePath+wartermark,imageName);//1:1压缩图存放位置
		 	        		 thumbnailator.setToPath(p1ImagePath);
		 	        		 String wmImageUrl =PictureUtils.thumbnailatorImage(thumbnailator);
		 	        		 wmImageUrl= webBasePath+wmImageUrl.replaceAll(saveRealBasePath, "");
		 	        		 dto.setWatermarkImageUrl(wmImageUrl);
		 	        	}else if(thumbnailator.getHeight()!=0 && thumbnailator.getWidth()!=0){
		 	        		 String tailorImagePath=PictureUtils.getImageSavePath(saveRealBasePath,imagePath+tailor+thumbnailator.getHeight(),imageName);//裁剪图片位置
		 	        		 thumbnailator.setToPath(tailorImagePath);
		 	        		 String tailorImageUrl =PictureUtils.thumbnailatorImage(thumbnailator);
			 	        	 tailorImageUrl = webBasePath+tailorImageUrl.replaceAll(saveRealBasePath, "");
			 	        	 dto.setTailorImageUrl(tailorImageUrl);
		 	        	}else {
		 	        		 if(thumbnailator.getScale()==0){
		 	        			thumbnailator.setScale(1f);
		 	        		 }
		 	        		 String p1ImagePath=PictureUtils.getImageSavePath(saveRealBasePath,imagePath+zipPath+(int)(thumbnailator.getScale()*100),imageName);//1:1压缩图存放位置
		 	        		 thumbnailator.setToPath(p1ImagePath);
		 	        		 String minImageUrl =PictureUtils.thumbnailatorImage(thumbnailator);
			 	        	 minImageUrl= webBasePath+minImageUrl.replaceAll(saveRealBasePath, "");
			 	        	 dto.setMinImageUrl(minImageUrl);
		 	        	}
		 	        }
		        }
		 }catch(Exception e){
			 e.printStackTrace();
		     dto.setErrorInfo(RETURN_STATUS.SYSTEM_ERROR.getValue(), e.getMessage());
		 }
		 return dto;
	}
	
	private String getOriginFileName(String fileName){
		int pos=fileName.lastIndexOf(".");
		return fileName.substring(0, pos);
	}


	@Override
	public String getWebContextPath() {
		return webContextPath;
	}

}
