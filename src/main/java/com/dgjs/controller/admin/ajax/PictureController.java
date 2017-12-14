package com.dgjs.controller.admin.ajax;


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

import com.alibaba.fastjson.JSON;
import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PictureDto;
import com.dgjs.model.dto.ThumbnailatorDto;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.result.view.EditorUploadPictureView;
import com.dgjs.model.result.view.UploadPictureView;
import com.dgjs.service.common.PictureService;

@Controller
@RequestMapping("/admin/static")
public class PictureController {

	private Log log = LogFactory.getLog(PictureController.class);
	
	@Autowired
	PictureService pictureService;
	
	@ResponseBody
	@RequestMapping(value = "/ajaxUpload")
	@LogRecord(operate=OperateEnum.Add,remark="上传图片")
	public String ajaxUpload(HttpServletRequest request, HttpServletResponse response,String imagePath,ThumbnailatorDto thumbnailator){
		 UploadPictureView view=new UploadPictureView();
	     try {
	    	 List<PictureDto> list = pictureService.uploadPic(request, imagePath,"uploadImage",thumbnailator);
	    	 view.setList(list);
	    } catch (Exception e) {
	        view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
	        log.error("ajaxUpload error", e);
	    }
	    return JSON.toJSONString(view);
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajaxUploadBase64",method=RequestMethod.POST)
	@LogRecord(operate=OperateEnum.Add,remark="上传图片base64")
	public String ajaxUploadBase64(String base64, HttpServletRequest request, HttpServletResponse response,String imagePath,ThumbnailatorDto thumbnailator){
		 UploadPictureView view=new UploadPictureView();
	     try {
	    	 List<PictureDto> list = pictureService.uploadPicBase64(base64, imagePath,"uploadImage",thumbnailator);
	    	 view.setList(list);
	    } catch (Exception e) {
	        view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
	        log.error("ajaxUpload error", e);
	    }
	    return JSON.toJSONString(view);
	}
	
	@ResponseBody
	@RequestMapping(value = "/ajaxUploadEditorImage")
	@LogRecord(operate=OperateEnum.Add,remark="上传富文本编辑图片")
	public String ajaxUploadEditorImage(HttpServletRequest request, HttpServletResponse response,String imagePath,ThumbnailatorDto thumbnailator){
		EditorUploadPictureView view=new EditorUploadPictureView();
	     try {
	    	 List<PictureDto> list = pictureService.uploadPic(request, imagePath,"imgFile",thumbnailator);
	         if(list==null||list.size()==0){
	        	view.setError(RETURN_STATUS.SYSTEM_ERROR.toString());
	        	return JSON.toJSONString(view);
	         }
	        view.setUrl(pictureService.getImageContextPath()+list.get(0).getMinImageUrl());
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
