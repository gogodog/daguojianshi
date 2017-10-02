package com.dgjs.controller.admin.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.persistence.Authority;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.admin.AuthorityService;

@Controller
@RequestMapping("/admin/admin")
public class AuthorityController {
	
	@Autowired
	AuthorityService  authorityService;

	@RequestMapping("/athrtyList")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("admin/admin/athrty_list");
		List<Authority> list=authorityService.list();
		mv.addObject("list", list);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveAthrty",method=RequestMethod.POST)
	public BaseView save(Authority authority){
		BaseView mv = new BaseView();
		if(StringUtils.isEmpty(authority.getAuthority_name())||
				authority.getAuthority_name().length()>50){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		if(StringUtils.isEmpty(authority.getAuthority_url())||
				authority.getAuthority_url().length()>200){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		int flag = authorityService.save(authority);
		if(flag < 1){
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return mv;
	}
}
 