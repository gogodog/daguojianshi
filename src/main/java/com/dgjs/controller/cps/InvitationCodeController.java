package com.dgjs.controller.cps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.Constants;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.InvitationCodeDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.InvitationCode;
import com.dgjs.model.persistence.condition.InvitationCondition;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.admin.InvitationCodeService;
import com.dgjs.utils.WebContextHelper;

@Controller
@RequestMapping("/cps/ivtatncd")
public class InvitationCodeController {
	
	@Autowired
	InvitationCodeService invitationCodeService;
	
	@RequestMapping("/list")
	public ModelAndView list(InvitationCondition condition){
		ModelAndView mv = new ModelAndView("/cps/ivtatncd");
		condition.setFromUserId(WebContextHelper.getUserId());
		PageInfoDto<InvitationCodeDto> pageinfo = invitationCodeService.list(condition);
		mv.addObject("pageinfo", pageinfo);
		mv.addObject("condition", condition);
		return mv;
	}

	@ResponseBody
	@RequestMapping("/produce")
	public BaseView produce(){
		BaseView bv = new BaseView();
		int count = invitationCodeService.getValidCount(WebContextHelper.getUserId());
		if(Constants.MAX_INVITATION <= count ){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.getValue(), "有效推荐码已达上限");
		}else{
			InvitationCode invitationCode = invitationCodeService.produce(WebContextHelper.getUserId());
			if(invitationCode==null){
				bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			}
			bv.setObjects(invitationCode);
		}
		return bv;
	}
	
}
