package com.dgjs.controller.cps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.constants.RegexPattern;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.persistence.AdminUser;
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
		Integer userId = WebContextHelper.getUserId();
		adminUserInfo.setId(userId);
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
		}else if(source==2){
			if(StringUtils.isNullOrEmpty(adminUserInfo.getMobile())||
					StringUtils.isNullOrEmpty(adminUserInfo.getAddress())||
		            StringUtils.isNullOrEmpty(adminUserInfo.getEmail())){
				bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
				return bv;
			}
			if(!RegexPattern.isMatch(RegexPattern.email, adminUserInfo.getEmail())){
				bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.getValue(),"邮箱格式错误");
				return bv;
			}
		}
		int flag=adminUserService.saveOrUpdateAdminUserInfo(adminUserInfo);
		if(flag<1){
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			return bv;
		}
		//之后改进
		AdminUser adminUser = WebContextHelper.getAdminUser();
		if(adminUser.getRole_id()==3){
		   	adminUserInfo = adminUserService.getAdminUserInfo(userId);
		   	//如果基本信息都填写了，具有编辑文章权限的条件
		   	if(adminUserInfo.isCanEditArticle()){
		   		adminUser.setRole_id(2);
		   	    flag = adminUserService.updateAdminUser(adminUser);
		   	    if(flag > 0){
		   	    	JSONObject jsonObject = new JSONObject();
		   	    	jsonObject.put("isNeedRedirect", true);
		   	    	bv.setObjects(jsonObject);
		   	    }
		   	}
		}
		return bv;
	}
}
