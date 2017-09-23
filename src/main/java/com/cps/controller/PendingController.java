package com.cps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.Constants;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.persistence.condition.PendingCondition;
import com.dgjs.service.content.PendingService;

@Controller
@RequestMapping("/cps")
public class PendingController {

	@Autowired
	PendingService pendingService;
	
	@RequestMapping("/docms")
	public ModelAndView docms(){
		ModelAndView mv = new ModelAndView("/cps/docms");
		PendingCondition condition = new PendingCondition();
		condition.setUserId(Constants.USER_ID);
		PageInfoDto<Pending> pageinfo = pendingService.listPending(condition);
		mv.addObject("pageinfo", pageinfo);
		return mv;
	}
	
	@RequestMapping("/previewPending")
	public ModelAndView previewDraft(String aid)  throws Exception{
		ModelAndView mv = new ModelAndView("front/common/show");
		Pending pending=pendingService.selectByIdAll(aid);
		mv.addObject("articlescrap", pending);
		return mv;
	}
}
