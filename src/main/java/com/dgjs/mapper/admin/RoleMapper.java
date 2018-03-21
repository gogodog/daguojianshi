package com.dgjs.mapper.admin;

import java.util.List;

import com.dgjs.model.persistence.Authority;
import com.dgjs.model.persistence.Role;
import com.dgjs.model.persistence.RoleAuthority;

public interface RoleMapper {

	public int save(Role role);
	
	public Role selectById(Integer id);
	
	public List<Role> selectByIds(List<Integer> list);
	
	public int update(Role role);
	
	public List<Role> list();
	
	public int saveRoleAuthority(List<RoleAuthority> list);
	
	public int deleteRole(Integer id);
	
	public int deleteRoleAuthority(Integer role_id);
	
	public List<RoleAuthority> isExistRelated(RoleAuthority roleAuthority);
	
	public List<Authority> getAuthoritysByRoleId(Integer role_id);
	
	
	//查询子角色id（包括自己）
	public List<Integer> getChildrenRoleIds(String roleCode);
	public List<Role> getChildrenRoles(String roleCode);
	
}
