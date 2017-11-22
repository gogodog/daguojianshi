package com.dgjs.controller.admin.content;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.Constants;
import com.dgjs.constants.EventCode;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.param.view.ArticleAudit;
import com.dgjs.model.param.view.ArticlePublish;
import com.dgjs.model.persistence.condition.PendingCondition;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.content.PendingService;
import com.dgjs.utils.DateUtils;

@Controller
@RequestMapping("/admin/pding")
public class APendingController {

	@Autowired
	PendingService pendingService;
	
	@RequestMapping("/docms")
	public ModelAndView docms(PendingCondition condition){
		ModelAndView mv = new ModelAndView("/admin/content/pending_list");
		condition.setUserId(Constants.USER_ID);
		Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
		sort.put("update_time", SortOrder.DESC);
		condition.setSort(sort);
		PageInfoDto<Pending> pageinfo = pendingService.listPending(condition);
		mv.addObject("pageInfo", pageinfo);
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
	
	@RequestMapping("/audit")
	@ResponseBody
	@LogRecord(operate=OperateEnum.Update,remark="审核文章",event=EventCode.AUDIT_NOTICE)
	public BaseView audit(ArticleAudit articleAudit) throws Exception{
		BaseView mv = new BaseView();
		String aid = articleAudit.getAid();
		Pending_Status status = articleAudit.getStatus();
		String audit_desc = articleAudit.getAudit_desc();
		if(StringUtils.isEmpty(aid)||status == null){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		if(status!=Pending_Status.Audit_FAIL && status!=Pending_Status.PUBLISH_PENDING){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		if(status==Pending_Status.Audit_FAIL&&StringUtils.isEmpty(audit_desc)){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		int flag = pendingService.audit(aid, status, Constants.USER_ID, audit_desc);
		if(flag<1){
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			return mv;
		}
		return mv;
	}
	
	@RequestMapping("/publish")
	@ResponseBody
	@LogRecord(operate=OperateEnum.Update,remark="发布文章")
	public BaseView publish(ArticlePublish articlePublish) throws Exception{
		BaseView mv = new BaseView();
		Date showTime = null;
		String aid = articlePublish.getAid();
		String show_time = articlePublish.getShow_time();
		Integer visits = articlePublish.getVisits();
		if(StringUtils.isEmpty(aid)){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		showTime = DateUtils.parseDateFromString(show_time);
		//如果展示时间为空，默认为当前时间
		if(showTime == null){
			showTime = new Date();
		}
		int flag=pendingService.publish(aid, Constants.USER_ID, visits==null?0:visits, showTime,articlePublish.getShowNow()==0?false:true);
		if(flag < 1){
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			return mv;
		}
		return mv;
	}
}
