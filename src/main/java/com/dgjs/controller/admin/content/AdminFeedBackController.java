package com.dgjs.controller.admin.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.dto.AdminFeedBackDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.condition.AdminFeedBackCondition;
import com.dgjs.service.content.AdminFeedBackService;

@Controller
@RequestMapping("/admin/afb")
public class AdminFeedBackController {
	
	@Autowired
	AdminFeedBackService adminFeedBackService;

	@RequestMapping("/feedBackList")
	public ModelAndView list(AdminFeedBackCondition condition){
		ModelAndView mv = new ModelAndView("admin/content/admin_feedbacks");
		PageInfoDto<AdminFeedBackDto> pageinfo=adminFeedBackService.list(condition);
		mv.addObject("pageInfo", pageinfo);
		mv.addObject("condition", condition);
		return mv;
	}
}
