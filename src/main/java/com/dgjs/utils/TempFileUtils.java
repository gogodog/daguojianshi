package com.dgjs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class TempFileUtils {

	public static final String blank=System.getProperty("file.separator");
	
	public static final String URLBlank="/";
	
	public static String uploadPicture(HttpServletRequest request,String name,String[] pathName,String imageName) throws Exception{
		boolean isUploadFile=false;
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request; 
        MultipartFile file  =  multipartRequest.getFile(name);
        InputStream inputStream= file.getInputStream();
        @SuppressWarnings("unused")
		int flag;
        byte[] buff=new byte[1024*1024];
        String realPath = request.getSession().getServletContext().getRealPath("/");   
        String suffix=".jpg";
        String[] pathArray={realPath,StringUtils.combineStr(pathName, blank),blank,imageName,suffix};
        String savePath=StringUtils.combineStr(pathArray,null);
        File outputfile=new File(savePath);
        outputfile.createNewFile();
        FileOutputStream outputStream =new FileOutputStream(outputfile,true);
        while((flag=inputStream.read(buff))!=-1){
        	outputStream.write(buff);
        	if(!isUploadFile){
        		isUploadFile=true;
        	}
        }
        inputStream.close();
        outputStream.close();
        if(!isUploadFile){
        	return null;
        }
        String[] showPathArray={request.getContextPath(),URLBlank,StringUtils.combineStr(pathName, "/"),URLBlank,imageName,suffix};
        return StringUtils.combineStr(showPathArray,null);
	}
	
	/**
	 * 日期+6位随机数
	 */
	public static String generateImageName(){
		String str=DateUtils.parseStringFromDate(new Date(), "yyyyMMddhhmmss");
		String random=getRandomNumber();
		return str+random;
	}
	
	private static String getRandomNumber(){
		Random random=new Random();
		StringBuilder str=new StringBuilder();
		for(int i=0;i<6;i++){
			str.append(random.nextInt(10));
		}
		return str.toString();
	}
	
}
