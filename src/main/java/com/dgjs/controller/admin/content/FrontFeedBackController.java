package com.dgjs.controller.admin.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.dto.FeedBackDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.condition.FrontFeedBackCondition;
import com.dgjs.service.content.FrontFeedBackService;

@Controller
@RequestMapping("/admin/fb")
public class FrontFeedBackController {

	@Autowired
	FrontFeedBackService feedBackService;
	
	@RequestMapping("/feedBackList")
	public ModelAndView list(FrontFeedBackCondition condition){
		ModelAndView mv = new ModelAndView("admin/content/feedbacks");
		condition.setNeedTotalResults(true);
		PageInfoDto<FeedBackDto> pageInfo=feedBackService.listFeedBack(condition);
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("condition",condition);
		return mv;
	}
}
