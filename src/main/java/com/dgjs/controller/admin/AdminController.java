package com.dgjs.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.RoleAuthorityDto;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.AdminUserInfo;
import com.dgjs.model.persistence.Organization;
import com.dgjs.model.persistence.Role;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.result.AdminUserResult;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.admin.OrganizationService;
import com.dgjs.service.admin.RoleService;
import com.dgjs.service.config.ConfigService;

@Controller
@RequestMapping("/admin/adnur")
public class AdminController {

	@Autowired
	AdminUserService adminUserService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ConfigService configService;
	
	@Autowired
	OrganizationService organizationService;
	
	@RequestMapping("/adminList")
	public ModelAndView adminList(AdminUserCondition condition){
		ModelAndView mv = new ModelAndView("admin/admin/admin_list");
		PageInfoDto<AdminUserResult> pageinfo = adminUserService.list(condition);
		mv.addObject("pageinfo", pageinfo);
		mv.addObject("condition", condition);
		List<Role> roleList = roleService.list();
		mv.addObject("roleList", roleList);
		mv.addObject("organizations", getOrganizationName(pageinfo));
		return mv;
	}
	
	private Map<String,String> getOrganizationName(PageInfoDto<AdminUserResult> pageinfo){
		if(pageinfo==null)
			return null;
		List<AdminUserResult> resultList = pageinfo.getObjects();
		if(resultList==null || resultList.isEmpty())
			return null;
		List<Integer> ids = new ArrayList<Integer>();
		for(AdminUserResult adminUser:resultList){
			ids.add(adminUser.getOrganization());
		}
		Map<String,String> map = new HashMap<String,String>();
		if(ids.size() > 0){
			List<Organization> orgList= organizationService.selectByIds(ids);
			for(Organization organization:orgList){
				map.put(String.valueOf(organization.getId()), organization.getOname());
			}
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/updateAdminStatus")
	@LogRecord(operate=OperateEnum.Update,remark="修改用户状态")
	public BaseView updateAdminStatus(Integer uid,Integer status){
		BaseView bv = new BaseView();
		if(uid == null || status == null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		AdminUser adminUser = new AdminUser();
		adminUser.setId(uid);
		adminUser.setStatus(UpDown_Status.valueOf(status));
		int flag = adminUserService.update(adminUser);
		if(flag < 1){
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return bv;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(Integer uid){
		ModelAndView mv = new ModelAndView("admin/admin/admin_detail");
		AdminUser adminUser = adminUserService.getAdminUser(uid);
		AdminUserInfo adminUserInfo = adminUserService.getAdminUserInfo(uid);
		RoleAuthorityDto role = roleService.selectById(adminUser.getRole_id());
		List<Role> allRole= roleService.list();
		mv.addObject("role", role.getRole());
		mv.addObject("allRole", allRole);
		mv.addObject("adminUser", adminUser);
		mv.addObject("adminUserInfo", adminUserInfo);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/updateAdminRole")
	@LogRecord(operate=OperateEnum.Update,remark="修改用户角色")
	public BaseView updateAdminRole(Integer uid,Integer role){
		BaseView bv = new BaseView();
		if(uid == null || role == null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		AdminUser adminUser = new AdminUser();
		adminUser.setRole_id(role);
		adminUser.setId(uid);
		int flag=adminUserService.update(adminUser);
		if(flag < 1){
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return bv;
	}
	
}
