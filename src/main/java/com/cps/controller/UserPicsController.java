package com.cps.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dgjs.constants.Constants;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PictureDto;
import com.dgjs.model.dto.ThumbnailatorDto;
import com.dgjs.model.dto.UserPicsDto;
import com.dgjs.model.persistence.UserPics;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.model.result.view.UploadPictureView;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.UserPicsService;

@Controller
@RequestMapping("/cps/userPics")
public class UserPicsController {
	
	@Autowired
	UserPicsService userPicsService;
	@Autowired
	PictureService pictureService;
	
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("/cps/source");
		UserPicsDto userPics=userPicsService.selectById(Constants.USER_ID);
		mv.addObject("userPics", userPics);
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		mv.addObject("container", Constants.MAX_CONTAINER);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajaxUpload")
	public String ajaxUpload(HttpServletRequest request, HttpServletResponse response,String imagePath,ThumbnailatorDto thumbnailator){
		 UploadPictureView view=new UploadPictureView();
	     try {
	    	 int capacity = 0;//还可上传多少张
	    	 UserPicsDto userPics=userPicsService.selectById(Constants.USER_ID);
	    	 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
	 	     List<MultipartFile> files=multipartRequest.getFiles("uploadImage");
	 	     if(files.size() > Constants.ONECE_MAX_CONTAINER){
	 	    	 view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"一次最多可上传"+Constants.ONECE_MAX_CONTAINER+"张图片");
	 	     }else{
	 	    	 if(userPics == null){
	 	    		//上传
	    			List<PictureDto> list = pictureService.uploadPic(request, imagePath,"uploadImage",thumbnailator);
	 	    		UserPicsDto userPicsDto = new UserPicsDto();
	 	    		UserPics upic = new UserPics();
	 	    		userPicsDto.setUserPics(upic);
	 	    		userPicsDto.setPics(getPics(list));
	 	    		upic.setId(Constants.USER_ID);
	 	    		int flag = userPicsService.save(userPicsDto);
	 	    		if(flag < 1){
	 	    			view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
	 	    		}
		    	 }else{
		    		 capacity = Constants.MAX_CONTAINER - (userPics.getPics()==null?0:userPics.getPics().size());
		    		 if(capacity < files.size()){
		    			 view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"每人素材库最多限"+Constants.MAX_CONTAINER +"张图片");
		    		 }else{
		    			 //上传
		    			 List<PictureDto> list = pictureService.uploadPic(request, imagePath,"uploadImage",thumbnailator);
		    			 int flag = userPicsService.update(Constants.USER_ID, 1, getPics(list));
		    			 if(flag < 1){
			 	    			view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			 	    	 }
		    		 }
		    	 }
	 	     }
	    } catch (Exception e) {
	        view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
	    }
	    return JSON.toJSONString(view);
	}
	
	private List<String> getPics(List<PictureDto> list){
		if(list == null || list.isEmpty()){
			return null;
		}
		List<String> l = new ArrayList<String>();
		for(PictureDto dto:list){
			l.add(dto.getMinImageUrl());
		}
		return l;
	}
	
	@ResponseBody
	@RequestMapping(value = "/remove")
	public BaseView remove(String pics){
		BaseView bv = new BaseView();
		List<String> list= JSON.parseArray(pics, String.class);
		int flag = userPicsService.update(Constants.USER_ID, 2, list);
		if(flag<1){
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return bv;
	}
}
