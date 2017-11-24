package com.dgjs.service.admin;

import java.util.List;

import com.dgjs.model.dto.RoleAuthorityDto;
import com.dgjs.model.persistence.Role;

public interface RoleService {

	public boolean isExist(Integer roleId,Integer authorizeId);
	
	public List<Role> list();
	
	public int deleteById(Integer id);
	
	public RoleAuthorityDto selectById(Integer id);
	
	public RoleAuthorityDto selectByIdMCache(Integer id);
}
