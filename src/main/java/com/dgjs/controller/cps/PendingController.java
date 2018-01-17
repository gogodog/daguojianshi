package com.dgjs.controller.cps;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.condition.DraftCondition;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.DraftAPRecordService;
import com.dgjs.service.content.DraftService;
import com.dgjs.utils.WebContextHelper;

@Controller
@RequestMapping("/cps/pding")
public class PendingController {

	@Autowired
	DraftService draftService;
	
	@Autowired
	PictureService pictureService;
	
	@Autowired
	DraftAPRecordService draftAPRecordService;
	
	@RequestMapping("/docms")
	public ModelAndView docms(DraftCondition condition){
		ModelAndView mv = new ModelAndView("/cps/docms");
		condition.setUserId(WebContextHelper.getUserId());
		Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
		sort.put("update_time", SortOrder.DESC);
		condition.setSort(sort);
		PageInfoDto<Draft> pageinfo = draftService.listDrafts(condition);
		Map<String,String> rejectMap= draftAPRecordService.getLastRejectMsg(pageinfo);
		mv.addObject("pageinfo", pageinfo);
		mv.addObject("condition",condition);
		mv.addObject("statusList", Pending_Status.values());
		mv.addObject("typeList", Articlescrap_Type.values());
		mv.addObject("rejectMap", rejectMap);
		return mv;
	}
	
	@RequestMapping("/previewPending")
	public ModelAndView previewDraft(String aid)  throws Exception{
		ModelAndView mv = new ModelAndView("front/admin/show");
		Draft draft=draftService.selectByIdAll(aid);
		mv.addObject("articlescrap", draft);
		return mv;
	}
	
}
