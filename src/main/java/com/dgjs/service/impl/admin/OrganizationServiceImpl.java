package com.dgjs.service.impl.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.AdminUserMapper;
import com.dgjs.mapper.admin.OrganizationMapper;
import com.dgjs.model.dto.OrganizationDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.Organization;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.condition.OrganizationCondition;
import com.dgjs.model.persistence.result.AdminUserResult;
import com.dgjs.service.admin.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService{

	@Autowired
	OrganizationMapper organizationMapper;
	
	@Autowired
	AdminUserMapper adminUserMapper;
	
	@Override
	public PageInfoDto<OrganizationDto> list(OrganizationCondition condition) {
		condition.setBeginNum((condition.getCurrentPage()-1)*condition.getOnePageSize());
		List<Organization> list = organizationMapper.list(condition);
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = organizationMapper.count(condition);
		}
		if(list!=null && !list.isEmpty()){
			List<Integer> proxyIds = new ArrayList<Integer>();
			for(Organization organization:list){
				proxyIds.add(organization.getProxy());
			}
			AdminUserCondition cdtion = new AdminUserCondition();
			cdtion.setAdminIds(proxyIds);
			List<AdminUserResult> resultList = adminUserMapper.list(cdtion);
			Map<Integer,AdminUserResult> map = new HashMap<Integer,AdminUserResult>();
			for(AdminUserResult result:resultList){
				map.put(result.getId(), result);
			}
			List<OrganizationDto> dtoList = new ArrayList<OrganizationDto>();
			for(Organization organization:list){
				OrganizationDto dto = new OrganizationDto();
				dto.setOrganization(organization);
				dto.setUsername(map.get(organization.getProxy()).getUsername());
				dto.setUserCode(map.get(organization.getProxy()).getUser_code());
				dtoList.add(dto);
			}
			return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, dtoList);
		}
		return null;
	}

	@Override
	public OrganizationDto selectById(Integer id) {
		Organization organization = organizationMapper.selectById(id);
		if(organization == null){
			return null;
		}
		OrganizationDto dto = new OrganizationDto();
		AdminUser adminUser = adminUserMapper.selectById(organization.getProxy());
		dto.setOrganization(organization);
		dto.setUsername(adminUser.getUsername());
		dto.setUserCode(adminUser.getUser_code());
		return dto;
	}

	@Override
	public List<Organization> selectByIds(List<Integer> ids) {
		return organizationMapper.selectByIds(ids);
	}

}
