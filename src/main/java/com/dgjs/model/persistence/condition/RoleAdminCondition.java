package com.dgjs.model.persistence.condition;

import java.util.List;

public class RoleAdminCondition extends BasePageCondition{

	private String username;
	
	private Integer organization;
	private List<Integer> roleIds;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getOrganization() {
		return organization;
	}
	public void setOrganization(Integer organization) {
		this.organization = organization;
	}
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
}
