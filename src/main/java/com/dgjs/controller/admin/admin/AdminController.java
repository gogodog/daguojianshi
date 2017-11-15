package com.dgjs.controller.admin.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.Role;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.result.AdminUserResult;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.admin.RoleService;

@Controller
@RequestMapping("/admin/adnur")
public class AdminController {

	@Autowired
	AdminUserService adminUserService;
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/adminList")
	public ModelAndView adminList(AdminUserCondition condition){
		ModelAndView mv = new ModelAndView("admin/admin/admin_list");
		PageInfoDto<AdminUserResult> pageinfo = adminUserService.list(condition);
		mv.addObject("pageinfo", pageinfo);
		mv.addObject("condition", condition);
		List<Role> roleList = roleService.list();
		mv.addObject("roleList", roleList);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/updateAdminStatus")
	public BaseView updateAdminStatus(Integer uid,Integer status){
		BaseView bv = new BaseView();
		if(uid == null || status == null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		AdminUser adminUser = new AdminUser();
		adminUser.setId(uid);
		adminUser.setStatus(UpDown_Status.valueOf(status));
		int flag = adminUserService.updateAdminUser(adminUser);
		if(flag < 1){
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return bv;
	}
}