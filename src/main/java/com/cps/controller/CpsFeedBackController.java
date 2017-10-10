package com.cps.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgjs.constants.Constants;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.enums.Feedback_Type;
import com.dgjs.model.persistence.FeedBack;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.content.FeedBackService;
import com.dgjs.utils.StringUtils;

@Controller
@RequestMapping("/cps")
public class CpsFeedBackController {

	@Autowired
	FeedBackService feedBackService;
	
	@RequestMapping("/feedback")
    public String feedback(HttpServletRequest request) throws Exception {  
		return "/cps/feedback";
    }
	
	@ResponseBody
	@RequestMapping("/saveFeedback")
	public BaseView saveFeedback(FeedBack feedBack){
		BaseView bv = new BaseView();
		if(StringUtils.isNullOrEmpty(feedBack.getJudge_message())){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv; 
		}
		feedBack.setUname(String.valueOf(Constants.USER_ID));
		feedBack.setFeedback_type(Feedback_Type.ADMIN);
		feedBackService.save(feedBack);
		return bv;
	}
}