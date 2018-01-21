package com.dgjs.controller.cps;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Announcement;
import com.dgjs.model.persistence.condition.AnnouncementCondition;
import com.dgjs.service.announcement.AnnouncementService;

@Controller
public class AnnouncementController {

	@Autowired
	AnnouncementService announcementService;
	
	@RequestMapping("/cps")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("/cps/announce");
		AnnouncementCondition condition = new AnnouncementCondition();
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, -3);
		condition.setStatus(UpDown_Status.UP);
		condition.setTimeFrom(calendar.getTime());
		condition.setTimeTo(now);
		condition.setOnePageSize(100);
		PageInfoDto<Announcement> pageinfo = announcementService.list(condition);
		if(pageinfo!=null && pageinfo.getObjects()!=null){
			mv.addObject("list", pageinfo.getObjects());
		}
		return mv;
	}
}
