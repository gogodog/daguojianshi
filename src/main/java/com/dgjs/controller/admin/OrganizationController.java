package com.dgjs.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.OrganizationDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.Organization;
import com.dgjs.model.persistence.condition.OrganizationCondition;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.admin.OrganizationService;
import com.dgjs.service.transaction.AdminUserTransactionService;

@Controller
@RequestMapping("/admin/ognztn")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;
	
	@Autowired
	AdminUserTransactionService adminUserTransactionService;
	
	@Autowired
	AdminUserService adminUserService;
	
	private Log log = LogFactory.getLog(OrganizationController.class);
	
	@RequestMapping("/list")
	public ModelAndView list(OrganizationCondition condition){
		ModelAndView mv = new ModelAndView("admin/admin/ognztn_list");
		PageInfoDto<OrganizationDto> pageinfo = organizationService.list(condition);
		mv.addObject("pageinfo", pageinfo);
		mv.addObject("condition", condition);
		return mv;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(Integer id){
		ModelAndView mv = new ModelAndView("admin/admin/ognztn");
		OrganizationDto dto = organizationService.selectById(id);
		mv.addObject("dto", dto);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public BaseView update(Organization organization,String proxyUserCode){
		BaseView bv = new BaseView();
		if(organization==null||StringUtils.isEmpty(organization.getOname())
				||StringUtils.isEmpty(organization.getSummary())||StringUtils.isEmpty(proxyUserCode)){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		AdminUser adminUser = adminUserService.getByUserCode(proxyUserCode);
		if(adminUser==null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.getValue(), "代理人code不存在");
			return bv;
		}
		organization.setProxy(adminUser.getId());
		try{
			adminUserTransactionService.updateOrganization(organization);
		}catch(Exception e){
			log.error("update organization exception", e);
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return bv;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public BaseView save(Organization organization,String proxyUserCode){
		BaseView bv = new BaseView();
		if(organization==null||StringUtils.isEmpty(organization.getOname())
				||StringUtils.isEmpty(organization.getSummary())||StringUtils.isEmpty(proxyUserCode)){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}	
		AdminUser adminUser = adminUserService.getByUserCode(proxyUserCode);
		if(adminUser==null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.getValue(), "代理人code不存在");
			return bv;
		}
		organization.setProxy(adminUser.getId());
		try{
			adminUserTransactionService.saveOrganization(organization);
		}catch(Exception e){
			log.error("update organization exception", e);
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return bv;
	}
}
