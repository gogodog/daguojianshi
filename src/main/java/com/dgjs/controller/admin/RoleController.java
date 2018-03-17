package com.dgjs.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.exceptions.TransactionException;
import com.dgjs.model.dto.RoleAuthorityDto;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.persistence.Authority;
import com.dgjs.model.persistence.Role;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.admin.AuthorityService;
import com.dgjs.service.admin.RoleService;
import com.dgjs.service.transaction.RoleTransactionService;

@Controller
@RequestMapping("/admin/admin")
public class RoleController {

	@Autowired
	RoleService roleService;
	@Autowired
	RoleTransactionService roleTransactionService;
	@Autowired
	AuthorityService authorityService;
	
	@RequestMapping("/roleList")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("admin/admin/role_list");
		List<Role> list = roleService.list();
		mv.addObject("list", list);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteRole",method=RequestMethod.POST)
	@LogRecord(operate=OperateEnum.Delete,remark="删除角色")
	public BaseView delete(Integer id){
		BaseView mv = new BaseView();
		if(id == null){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		boolean isExist = roleService.isExist(id, null);
		if(isExist){
			mv.setBaseViewValue(RETURN_STATUS.SERVICE_ERROR.name(),"橘色已经关联权限");
			return mv;
		}
		int flag = roleService.deleteById(id);
		if(flag < 1){
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			return mv;
		}
		return mv;
	} 
	
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateRole",method=RequestMethod.POST)
	@LogRecord(operate=OperateEnum.Update,remark="保存角色")
	public BaseView saveOrUpdate(Integer roleId,String roleName,String authorityIds,Integer parentRole){
		BaseView mv = new BaseView();
		if(StringUtils.isEmpty(roleName)){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		try{
			Integer[] idArray = null;
			if(!StringUtils.isEmpty(authorityIds)){
				String[] ids= authorityIds.split(",");
				idArray = new  Integer[ids.length];
				int index = 0;
				for(String id:ids){
					idArray[index++] = Integer.parseInt(id);
				}
			}
			roleTransactionService.saveRole(roleId, roleName,parentRole, idArray);
		}catch(TransactionException e){
			mv.setBaseViewValue(e.getReturnData().getErrorCode(), e.getReturnData().getErrorMessage());
		}
		return mv;
	}
	
	@RequestMapping("/role")
	public ModelAndView selectById(Integer id){
		ModelAndView mv = new ModelAndView("admin/admin/role");
		if(id != null){
			RoleAuthorityDto dto = roleService.selectById(id);
			mv.addObject("dto", dto);
		}
		List<Authority>  authorityList = authorityService.list();
		mv.addObject("authorityList", authorityList);
		List<Role> roleList = roleService.list();
		mv.addObject("roleList", roleList);
		return mv;
	}
}
