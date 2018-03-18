package com.dgjs.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Announcement_Type;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Announcement;
import com.dgjs.model.persistence.condition.AnnouncementCondition;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.announcement.AnnouncementService;

@Controller
@RequestMapping("/admin/announce")
public class AAnnouncementController {

	@Autowired
	AnnouncementService announcementService;
	
	@RequestMapping("/list")
	public ModelAndView list(AnnouncementCondition condition){
		ModelAndView mv = new ModelAndView("/admin/announce/announcements");
		PageInfoDto<Announcement> pageInfo = announcementService.list(condition);
		mv.addObject("pageInfo", pageInfo);
		mv.addObject("condition",condition);
		mv.addObject("upDownStatus", UpDown_Status.values());
		mv.addObject("types", Announcement_Type.values());
		return mv;
	}
	
	@LogRecord(operate=OperateEnum.Add,remark="修改通告状态")
	@RequestMapping("/updateStatus")
	@ResponseBody
	public BaseView updateStatus(Integer id,UpDown_Status status){
		BaseView bv = new BaseView();
		if(id==null||status==null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		int flag = announcementService.updateStatus(id, status);
		if(flag < 1){
			bv.setBaseViewValue(RETURN_STATUS.SERVICE_ERROR);
		}
		return bv;
	}
	
	@LogRecord(operate=OperateEnum.Add,remark="新增通告")
	@RequestMapping("/save")
	@ResponseBody
	public BaseView save(String message,Announcement_Type type){
		BaseView bv = new BaseView();
		if(StringUtils.isEmpty(message)||type == null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		Announcement announcement = new Announcement();
		announcement.setMessage(message);
		announcement.setStatus(UpDown_Status.DOWN);
		announcement.setType(type);
		int flag = announcementService.save(announcement);
		if(flag < 1){
			bv.setBaseViewValue(RETURN_STATUS.SERVICE_ERROR);
		}
		return bv;
	} 
}
