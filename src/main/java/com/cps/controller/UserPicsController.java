package com.cps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.Constants;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PictureDto;
import com.dgjs.model.dto.ThumbnailatorDto;
import com.dgjs.model.dto.UserPicsDto;
import com.dgjs.model.dto.business.entity.Pics;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.persistence.UserPics;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.model.result.view.UploadPictureView;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.UserPicsService;
import com.dgjs.utils.WebContextHelper;

@Controller
@RequestMapping("/cps/userPics")
public class UserPicsController {
	
	@Autowired
	UserPicsService userPicsService;
	@Autowired
	PictureService pictureService;
	
	@RequestMapping("/list")
	@LogRecord(operate=OperateEnum.Browse,remark="浏览素材库")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("/cps/source");
		UserPicsDto userPics=userPicsService.selectById(WebContextHelper.getUserId());
		mv.addObject("userPics", userPics);
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		mv.addObject("container", Constants.MAX_CONTAINER);
		mv.addObject("fileSize", Constants.MAX_FILE_SIZE);
		mv.addObject("onceContainer", Constants.ONECE_MAX_CONTAINER);
		mv.addObject("userId", WebContextHelper.getUserId());
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/ajaxList")
	@LogRecord(operate=OperateEnum.Browse,remark="浏览素材库（草稿箱浏览）")
	public String ajaxList(){
		JSONArray jsa = new JSONArray();
		UserPicsDto userPics=userPicsService.selectById(WebContextHelper.getUserId());
		if(userPics!=null&&userPics.getPics()!=null&&!userPics.getPics().isEmpty()){
			for(Pics userPic:userPics.getPics()){
				JSONObject jso1 = new JSONObject();
				jso1.put("address", pictureService.getImageContextPath()+userPic.getUrl());
				jso1.put("name", userPic.getName());
				jsa.add(jso1);
			}
		}
		return jsa.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajaxUpload")
	@LogRecord(operate=OperateEnum.Add,remark="素材上传")
	public String ajaxUpload(HttpServletRequest request, HttpServletResponse response,String imagePath,ThumbnailatorDto thumbnailator){
		 UploadPictureView view=new UploadPictureView();
	     try {
	    	 int capacity = 0;//还可上传多少张
	    	 UserPicsDto userPics=userPicsService.selectById(WebContextHelper.getUserId());
	    	 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
	 	     List<MultipartFile> files=multipartRequest.getFiles("uploadImage");
	 	     if(files.size() > Constants.ONECE_MAX_CONTAINER){
	 	    	 view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"一次最多可上传"+Constants.ONECE_MAX_CONTAINER+"张图片");
	 	     }else{
	 	    	 //如果用户还没有图片信息
	 	    	 if(userPics == null){
	 	    		//上传
	    			List<PictureDto> list = pictureService.uploadPic(request, imagePath,"uploadImage",thumbnailator);
	 	    		UserPicsDto userPicsDto = new UserPicsDto();
	 	    		UserPics upic = new UserPics();
	 	    		userPicsDto.setUserPics(upic);
	 	    		getPics(view,userPicsDto,list);
	 	    		upic.setId(WebContextHelper.getUserId());
	 	    		int flag = userPicsService.save(userPicsDto);
	 	    		if(flag < 1){
	 	    			view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
	 	    		}
		    	 }else{
		    		 //算出还可上传多少张
		    		 capacity = Constants.MAX_CONTAINER - (userPics.getPics()==null?0:userPics.getPics().size());
		    		 if(capacity < files.size()){
		    			 view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"每人素材库最多限"+Constants.MAX_CONTAINER +"张图片");
		    		 }else{
		    			 //上传
		    			 List<PictureDto> list = pictureService.uploadPic(request, imagePath,"uploadImage",thumbnailator);
		    			 int flag = userPicsService.savePics(WebContextHelper.getUserId(), getPics(view,null,list));
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
	
	private List<Pics> getPics(UploadPictureView view,UserPicsDto userPicsDto,List<PictureDto> list){
		if(list == null || list.isEmpty()){
			return null;
		}
		List<Pics> l = new ArrayList<Pics>();
		for(PictureDto dto:list){
			if(dto.getIsSuccess()){
				Pics pics = new Pics();
				pics.setUrl(dto.getMinImageUrl());
				pics.setName(dto.getOriginName());
				l.add(pics);
			}
		}
		if(userPicsDto!=null){
			userPicsDto.setPics(l);
		}
		if(l.size()==0){
			view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.getValue(),"请上传图片格式，尺寸不得超过"+Constants.ONECE_MAX_CONTAINER);
		}
		return l;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/remove")
	@LogRecord(operate=OperateEnum.Delete,remark="删除素材")
	public BaseView remove(String pics){
		BaseView bv = new BaseView();
		List<String> list= JSON.parseArray(pics, String.class);
		int flag = userPicsService.removePics(WebContextHelper.getUserId(), list);
		if(flag<1){
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return bv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatePicName")
	@LogRecord(operate=OperateEnum.Update,remark="修改素材名称")
	public BaseView updatePicName(String picsJson){
		BaseView bv = new BaseView();
		List<Pics> pics= JSON.parseArray(picsJson, Pics.class);
		UserPicsDto userPicsDto = new UserPicsDto();
		userPicsDto.setPics(pics);
		UserPics userPics = new UserPics();
		userPics.setId(WebContextHelper.getUserId());
		userPicsDto.setUserPics(userPics);
		int flag = userPicsService.update(userPicsDto);
		if(flag<1){
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return bv;
	}
}
