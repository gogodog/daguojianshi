package com.dgjs.controller.admin.ajax;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PictureDto;
import com.dgjs.model.view.EditorUploadPictureView;
import com.dgjs.model.view.UploadPictureView;
import com.dgjs.service.common.PictureService;

@Controller
@RequestMapping("/admin")
public class PictureController {

	private Log log = LogFactory.getLog(PictureController.class);
	
	@Autowired
	PictureService pictureService;
	
	@ResponseBody
	@RequestMapping(value = "/ajaxUpload")
	public String ajaxUpload(HttpServletRequest request, HttpServletResponse response,String imagePath){
		 UploadPictureView view=new UploadPictureView();
	     try {
	        PictureDto dto=pictureService.uploadPic(request, imagePath,"uploadImage");
	        if(dto==null||!dto.getIsSuccess()){
	        	view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
	        	return JSON.toJSONString(view);
	        }
	        view.setImageUrl(dto.getMinImageUrl());
	    } catch (Exception e) {
	        view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
	        log.error("ajaxUpload error", e);
	    }
	    return JSON.toJSONString(view);
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajaxUploadEditorImage")
	public String ajaxUploadEditorImage(HttpServletRequest request, HttpServletResponse response,String imagePath){
		EditorUploadPictureView view=new EditorUploadPictureView();
	     try {
	        PictureDto dto=pictureService.uploadPic(request, imagePath,"imgFile");
	        if(dto==null||!dto.getIsSuccess()){
	        	view.setError(RETURN_STATUS.SYSTEM_ERROR.toString());
	        	return JSON.toJSONString(view);
	        }
	        view.setUrl(pictureService.getImageContextPath()+dto.getMinImageUrl());
	        view.setError("0");
	    } catch (Exception e) {
	        log.error("ajaxUpload error", e);
	        view.setError(RETURN_STATUS.SYSTEM_ERROR.toString());
	    }
	    return JSON.toJSONString(view);
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/ajaxUploadEditorImage")
//	public String ajaxUploadEditorImage(HttpServletRequest request, HttpServletResponse response,String imagePath){
//		EditorUploadPictureView view=new EditorUploadPictureView();
//	     try {
//	    	ReturnData<List<com.dgjs.model.outside.PictureDto>> returnData=pictureService.uploadPic(request, imagePath);
//            System.out.println(JSON.toJSONString(returnData, true));
//	     } catch (Exception e) {
//	        log.error("ajaxUpload error", e);
//	        view.setError(RETURN_STATUS.SYSTEM_ERROR.toString());
//	    }
//	    return JSON.toJSONString(view);
//	}
}
