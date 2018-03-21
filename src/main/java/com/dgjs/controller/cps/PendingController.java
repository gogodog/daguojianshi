package com.dgjs.controller.cps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.condition.DraftCondition;
import com.dgjs.model.persistence.condition.RoleAdminCondition;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.content.DraftAPRecordService;
import com.dgjs.service.content.DraftService;
import com.dgjs.utils.WebContextHelper;

@Controller
@RequestMapping("/cps/pding")
public class PendingController {

	@Autowired
	DraftService draftService;
	
	@Autowired
	DraftAPRecordService draftAPRecordService;
	
	@Autowired
	AdminUserService adminUserService;
	
	@RequestMapping("/docms")
	public ModelAndView docms(DraftCondition condition){
		ModelAndView mv = new ModelAndView("/cps/docms");
		condition.setUserId(WebContextHelper.getUserId());
		Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
		sort.put("update_time", SortOrder.DESC);
		condition.setSort(sort);
		if(condition.getStatus()==null){
			List<Pending_Status> statusList = Arrays.asList(Pending_Status.Audit_FAIL,Pending_Status.AUDIT_PENDING,Pending_Status.PUBLISH_PENDING,Pending_Status.PUBLISHED);
			condition.setStatusList(statusList);
		}
		setMemberCondition(WebContextHelper.getAdminUser(),condition);
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
	
	private void setMemberCondition(AdminUser adminUser,DraftCondition condition){
		RoleAdminCondition roleCondition =  new RoleAdminCondition();
		if(adminUser.getOrganization()!=null && condition.getMember()!=0){
			List<Integer> ids = adminUserService.getAdminIdsByRole(adminUser, roleCondition);
			if(condition.getMember() == 1){
				ids.remove(WebContextHelper.getUserId());
			}
			condition.setUserIdList(ids);
			condition.setStatusList(Arrays.asList(Pending_Status.PUBLISHED));
		}
	}
}
