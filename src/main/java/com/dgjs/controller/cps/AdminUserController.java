package com.dgjs.controller.cps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.persistence.AdminUserInfo;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.utils.StringUtils;
import com.dgjs.utils.WebContextHelper;

@Controller
@RequestMapping("/cps/user")
public class AdminUserController {

	@Autowired
	AdminUserService adminUserService;
	
	@RequestMapping("/psoninf")
	public ModelAndView userInfo(){
		ModelAndView mv = new ModelAndView("/cps/psoninf");
		AdminUserInfo adminUserInfo = adminUserService.getAdminUserInfo(WebContextHelper.getUserId());
		mv.addObject("adminUserInfo", adminUserInfo);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/editUserInfo")
	@LogRecord(operate=OperateEnum.Update,remark="修改个人信息")
	public BaseView editUserInfo(AdminUserInfo adminUserInfo,Integer source){
		BaseView bv = new BaseView();
		adminUserInfo.setId(WebContextHelper.getUserId());
		//保存基本信息，需要校验参数
		if(source==1){
			if(StringUtils.isNullOrEmpty(adminUserInfo.getReal_name())||adminUserInfo.getAge()<1
					||StringUtils.isNullOrEmpty(adminUserInfo.getOrganization())){
				bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
				return bv;
			}
			if(!(adminUserInfo.getSex()==1||adminUserInfo.getSex()==2)){
				bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
				return bv;
			}
		}
		int flag=adminUserService.saveOrUpdateAdminUserInfo(adminUserInfo);
		if(flag<1){
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			return bv;
		}
		return bv;
	}
}
