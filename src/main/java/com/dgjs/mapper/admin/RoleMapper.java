package com.dgjs.mapper.admin;

import java.util.List;

import com.dgjs.model.persistence.Role;
import com.dgjs.model.persistence.RoleAuthority;

public interface RoleMapper {

	public int save(Role role);
	
	public Role selectById(Integer id);
	
	public int update(Role role);
	
	public List<Role> list();
	
	public int saveRoleAuthority(RoleAuthority roleAuthority);
	
	public int deleteRole(Integer id);
	
	public int deleteRoleAuthority(Integer id);
	
}
