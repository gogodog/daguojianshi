package com.dgjs.model.dto;

import java.util.List;

import com.dgjs.model.persistence.Authority;
import com.dgjs.model.persistence.Role;

public class RoleAuthorityDto {

	private Role role;
	private List<Authority> authoritys;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Authority> getAuthoritys() {
		return authoritys;
	}
	public void setAuthoritys(List<Authority> authoritys) {
		this.authoritys = authoritys;
	}
	
}
