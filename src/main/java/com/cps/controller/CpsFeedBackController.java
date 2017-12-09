package com.cps.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.persistence.AdminFeedBack;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.content.AdminFeedBackService;
import com.dgjs.utils.StringUtils;
import com.dgjs.utils.WebContextHelper;

@Controller
@RequestMapping("/cps/fb")
public class CpsFeedBackController {

	@Autowired
	AdminFeedBackService feedBackService;
	
	@RequestMapping("/feedback")
	@LogRecord(operate=OperateEnum.Browse,remark="进入反馈页面")
    public String feedback(HttpServletRequest request) throws Exception {  
		return "/cps/feedback";
    }
	
	@ResponseBody
	@RequestMapping("/saveFeedback")
	@LogRecord(operate=OperateEnum.Add,remark="后台用户反馈信息")
	public BaseView saveFeedback(AdminFeedBack feedBack){
		BaseView bv = new BaseView();
		if(StringUtils.isNullOrEmpty(feedBack.getMessage())){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv; 
		}
		feedBack.setUserId(WebContextHelper.getUserId());
		feedBack.setHaveFeedBack(false);
		feedBackService.save(feedBack);
		return bv;
	}
}