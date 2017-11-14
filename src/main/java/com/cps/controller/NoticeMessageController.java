package com.cps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dgjs.constants.Constants;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.NoticeMessage;
import com.dgjs.model.persistence.condition.NoticeMessageCondition;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.admin.NoticeMessageService;

@Controller
@RequestMapping("/cps/ntcmsg")
public class NoticeMessageController {

	@Autowired
	NoticeMessageService noticeMessageService;
	
	@RequestMapping("/list")
	public ModelAndView list(NoticeMessageCondition condtion){
		ModelAndView mv = new ModelAndView("/cps/ntcmsg_list");
		condtion.setAdminId(Constants.USER_ID);
		PageInfoDto<NoticeMessage> pageInfo = noticeMessageService.list(condtion);
		mv.addObject("pageinfo", pageInfo);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/readMessage")
	public BaseView readMessage(String ids){
		BaseView bv = new BaseView();
		List<Long> list= JSON.parseArray(ids, Long.class);
		if(list == null || list.isEmpty()){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		}
		int size = noticeMessageService.readMessage(list);
	    if(size!=list.size()){
	    	bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
	    }
		return bv;
	}
}
