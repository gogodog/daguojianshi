package com.dgjs.controller.cps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.condition.RoleAdminCondition;
import com.dgjs.model.persistence.result.AdminUserResult;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.utils.WebContextHelper;

@Controller
@RequestMapping("/cps/childUser")
public class ChildUserController {
   
	@Autowired
	AdminUserService adminUserService;
	
	
	@RequestMapping("/list")
	public ModelAndView list(RoleAdminCondition condition){
		ModelAndView mv = new ModelAndView("/cps/childUserList");
		AdminUser adminUser = WebContextHelper.getAdminUser();
		if(adminUser.getOrganization()!=null){
			PageInfoDto<AdminUserResult> pageinfo = adminUserService.getAdminByRole(adminUser, condition);
			mv.addObject("pageinfo", pageinfo);
			mv.addObject("condition",condition);
		}
		return mv;
	}
	
}
