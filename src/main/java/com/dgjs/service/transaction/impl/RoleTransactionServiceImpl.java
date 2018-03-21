package com.dgjs.service.transaction.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgjs.constants.Constants;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.exceptions.TransactionException;
import com.dgjs.mapper.admin.PrimaryKeyMapper;
import com.dgjs.mapper.admin.RoleMapper;
import com.dgjs.model.persistence.Role;
import com.dgjs.model.persistence.RoleAuthority;
import com.dgjs.service.transaction.RoleTransactionService;

@Service
public class RoleTransactionServiceImpl implements RoleTransactionService{

	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	PrimaryKeyMapper primaryKeyMapper;
	
	@Transactional
	@Override
	public void saveRole(Integer roleId,String roleName,Integer parentRole, Integer[] authorityIds) {
		int flag = 0;
		if(roleId == null){
			String roleCode = null;
			int deep = 1;
			Long primaryValue = primaryKeyMapper.getPrimaryValue(Constants.ROLECODE_PRIMARY_ID);
			//父角色
			if(parentRole!=null){
				Role parent = roleMapper.selectById(parentRole);
				if(parent==null){
					throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
				}
				roleCode = parent.getRole_code().concat(String.valueOf(primaryValue)).concat("_");
				deep = parent.getDeep()+1;
			}else{
				roleCode = String.valueOf(primaryValue).concat("_");
			}
			Role role = new Role();
			role.setRole_name(roleName);
			role.setRole_code(roleCode);
			role.setDeep(deep);
			flag = roleMapper.save(role);
			if(flag > 0){
				roleId = role.getId();
			}else{
				throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue(),"save role exception");
			}
			flag = primaryKeyMapper.updateValue(Constants.ROLECODE_PRIMARY_ID, ++primaryValue);
			if(flag<1){
				throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue(),"primarykey updateValue exception");
			}
		}else{
			Role role = roleMapper.selectById(roleId);
			if(role == null){
				throw new TransactionException(RETURN_STATUS.PARAM_ERROR.getValue());
			}
			role.setRole_name(roleName);
			flag = roleMapper.update(role);
			if(flag < 1 ){
				throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue(),"update role exception");
			}
		}
	    roleMapper.deleteRoleAuthority(roleId);
		if(authorityIds!=null && authorityIds.length>0){
			List<RoleAuthority> list = new ArrayList<RoleAuthority>();
			for(Integer authorityId:authorityIds){
				RoleAuthority roleAuthority = new RoleAuthority();
				roleAuthority.setAuthority_id(authorityId);
				roleAuthority.setRole_id(roleId);
				list.add(roleAuthority);
			}
			flag = roleMapper.saveRoleAuthority(list);
			if(flag != list.size()){
				throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue(),"save roleAuthority exception");
			}
		}
	}

}
