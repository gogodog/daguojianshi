package com.dgjs.service.impl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.RoleMapper;
import com.dgjs.model.persistence.RoleAuthority;
import com.dgjs.service.admin.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public boolean isExist(Integer roleId, Integer authorizeId) {
		RoleAuthority ra = new RoleAuthority();
		ra.setAuthority_id(authorizeId);
		ra.setRole_id(roleId);
		List<RoleAuthority> list=roleMapper.selectRoleAuthority(ra);
		return !(list == null || list.size() == 0);
	}

}
