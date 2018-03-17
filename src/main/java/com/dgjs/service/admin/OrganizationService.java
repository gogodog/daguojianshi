package com.dgjs.service.admin;

import java.util.List;

import com.dgjs.model.dto.OrganizationDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Organization;
import com.dgjs.model.persistence.condition.OrganizationCondition;

public interface OrganizationService {

	public PageInfoDto<OrganizationDto> list(OrganizationCondition condition);
	
	public OrganizationDto selectById(Integer id);
	
	public List<Organization> selectByIds(List<Integer> ids);
}
