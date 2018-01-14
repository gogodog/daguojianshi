package com.dgjs.controller.admin;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.EventCode;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.param.view.ArticleAudit;
import com.dgjs.model.param.view.ArticlePublish;
import com.dgjs.model.persistence.DraftAPRecord;
import com.dgjs.model.persistence.condition.DraftCondition;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.content.DraftAPRecordService;
import com.dgjs.service.content.DraftService;
import com.dgjs.utils.DateUtils;
import com.dgjs.utils.WebContextHelper;

@Controller
@RequestMapping("/admin/pding")
public class APendingController {

	@Autowired
	DraftService draftService;
	
	@Autowired
	DraftAPRecordService draftAPRecordService;
	
	@RequestMapping("/docms")
	public ModelAndView docms(DraftCondition condition){
		ModelAndView mv = new ModelAndView("/admin/content/pending_list");
		Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
		sort.put("update_time", SortOrder.DESC);
		condition.setSort(sort);
		PageInfoDto<Draft> pageinfo = draftService.listDrafts(condition);
		mv.addObject("pageInfo", pageinfo);
		mv.addObject("condition",condition);
		mv.addObject("statusList", Pending_Status.values());
		mv.addObject("typeList", Articlescrap_Type.values());
		return mv;
	}
	
	@RequestMapping("/previewPending")
	public ModelAndView previewDraft(String aid)  throws Exception{
		ModelAndView mv = new ModelAndView("front/admin/show");
		Draft draft=draftService.selectByIdAll(aid);
		mv.addObject("articlescrap", draft);
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
		int flag = draftService.audit(aid, status, WebContextHelper.getUserId(), audit_desc);
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
		Long visits = articlePublish.getVisits();
		if(StringUtils.isEmpty(aid)){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		showTime = DateUtils.parseDateFromString(show_time);
		//如果展示时间为空，默认为当前时间
		if(showTime == null){
			showTime = new Date();
		}
		Draft draft = draftService.selectById(aid);
		if(draft == null){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		int flag=draftService.publish(aid, WebContextHelper.getUserId(), visits==null?0:visits, showTime,articlePublish.getShowNow()==0?false:true);
		if(flag < 1){
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return mv;
	}
	
	@RequestMapping("/aprecord")
	public ModelAndView aprecord(String aid)  throws Exception{
		ModelAndView mv = new ModelAndView("admin/content/aprecord");
	    List<DraftAPRecord> list = draftAPRecordService.list(Arrays.asList(aid), Arrays.asList(Pending_Status.Audit_FAIL,Pending_Status.PUBLISH_PENDING,Pending_Status.PUBLISHED), null);
		mv.addObject("list", list);
		return mv;
	}
}
