package com.cps.controller;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.Constants;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.condition.PendingCondition;
import com.dgjs.service.content.PendingService;

@Controller
@RequestMapping("/cps")
public class PendingController {

	@Autowired
	PendingService pendingService;
	
	@RequestMapping("/docms")
	public ModelAndView docms(PendingCondition condition){
		ModelAndView mv = new ModelAndView("/cps/docms");
		condition.setUserId(Constants.USER_ID);
		Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
		sort.put("update_time", SortOrder.DESC);
		condition.setSort(sort);
		PageInfoDto<Pending> pageinfo = pendingService.listPending(condition);
		mv.addObject("pageinfo", pageinfo);
		mv.addObject("condition",condition);
		mv.addObject("statusList", Pending_Status.values());
		mv.addObject("typeList", Articlescrap_Type.values());
		return mv;
	}
	
	@RequestMapping("/previewPending")
	public ModelAndView previewDraft(String aid)  throws Exception{
		ModelAndView mv = new ModelAndView("front/admin/show");
		Pending pending=pendingService.selectByIdAll(aid);
		mv.addObject("articlescrap", pending);
		return mv;
	}
	
}
