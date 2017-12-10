package com.dgjs.controller.admin.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.AdminFeedBackDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Message_Related_Type;
import com.dgjs.model.persistence.AdminFeedBack;
import com.dgjs.model.persistence.NoticeMessage;
import com.dgjs.model.persistence.condition.AdminFeedBackCondition;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.admin.NoticeMessageService;
import com.dgjs.service.content.AdminFeedBackService;
import com.dgjs.service.transaction.NtcMsgRltdTransactionService;

@Controller
@RequestMapping("/admin/afb")
public class AdminFeedBackController {
	
	@Autowired
	AdminFeedBackService adminFeedBackService;
	
	@Autowired
	NtcMsgRltdTransactionService noticeMessageRelatedService;
	
	@Autowired
	NoticeMessageService noticeMessageService;

	@RequestMapping("/feedBackList")
	public ModelAndView list(AdminFeedBackCondition condition){
		ModelAndView mv = new ModelAndView("admin/content/admin_feedbacks");
		PageInfoDto<AdminFeedBackDto> pageinfo=adminFeedBackService.list(condition);
		mv.addObject("pageInfo", pageinfo);
		mv.addObject("condition", condition);
		return mv;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(Long relatedId){
		ModelAndView mv = new ModelAndView("admin/content/admin_feedback");
		List<NoticeMessage> list = noticeMessageService.getByTypeRelatedId(Message_Related_Type.FeedBack, relatedId);
		mv.addObject("list", list);
		mv.addObject("relatedId", relatedId);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/reply")
	public BaseView reply(Long feedbackId,String message){
		BaseView bv = new BaseView();
		if(feedbackId == null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		AdminFeedBack adminFeedBack = adminFeedBackService.selectById(feedbackId);
		if(adminFeedBack==null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		String replyMessage=combineReplyMessage(adminFeedBack,message);
		noticeMessageRelatedService.saveNoticeMessageRelated(adminFeedBack.getUserId(), replyMessage, feedbackId, Message_Related_Type.FeedBack);
		return bv;
	}
	
	private String combineReplyMessage(AdminFeedBack adminFeedBack,String message){
		if(adminFeedBack == null){
			return null;
		}
		return com.dgjs.utils.StringUtils.jointString("针对于反馈消息","[",adminFeedBack.getMessage(),"]的回复信息:",message);
	}
}
