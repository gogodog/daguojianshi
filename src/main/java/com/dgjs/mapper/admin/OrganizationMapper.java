package com.dgjs.mapper.admin;

import java.util.List;

import com.dgjs.model.persistence.Organization;
import com.dgjs.model.persistence.condition.OrganizationCondition;

public interface OrganizationMapper {

	public int save(Organization organization);
	
	public Organization selectById(Integer id);
	
	public int update(Organization organization);
	
	public List<Organization> list(OrganizationCondition condition);
}
