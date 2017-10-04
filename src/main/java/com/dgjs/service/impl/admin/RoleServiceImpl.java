package com.dgjs.service.impl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.RoleMapper;
import com.dgjs.model.dto.RoleAuthorityDto;
import com.dgjs.model.persistence.Authority;
import com.dgjs.model.persistence.Role;
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
		List<RoleAuthority> list=roleMapper.isExistRelated(ra);
		return !(list == null || list.size() == 0);
	}
	
	@Override
	public List<Role> list(){
		return roleMapper.list();
	}

	@Override
	public int deleteById(Integer id) {
		return roleMapper.deleteRole(id);
	}

	@Override
	public RoleAuthorityDto selectById(Integer id) {
		RoleAuthorityDto dto = new RoleAuthorityDto();
		Role role = roleMapper.selectById(id);
		List<Authority> authoritys=roleMapper.getAuthoritysByRoleId(id);
		dto.setRole(role);
		dto.setAuthoritys(authoritys);
		return dto;
	}

}
