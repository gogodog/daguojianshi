package com.dgjs.service.transaction.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.exceptions.TransactionException;
import com.dgjs.mapper.admin.RoleMapper;
import com.dgjs.model.persistence.Role;
import com.dgjs.model.persistence.RoleAuthority;
import com.dgjs.service.transaction.RoleTransactionService;

@Service
public class RoleTransactionServiceImpl implements RoleTransactionService{

	@Autowired
	RoleMapper roleMapper;
	
	@Transactional
	@Override
	public void saveRole(Integer roleId,String roleName, Integer[] authorityIds) {
		int flag = 0;
		if(roleId == null){
			Role role = new Role();
			role.setRole_name(roleName);
			flag = roleMapper.save(role);
			if(flag > 0){
				roleId = role.getId();
			}else{
				throw new TransactionException(RETURN_STATUS.SYSTEM_ERROR.getValue(),"save role exception");
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
