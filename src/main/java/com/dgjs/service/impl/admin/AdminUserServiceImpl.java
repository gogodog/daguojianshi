package com.dgjs.service.impl.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.AdminUserInfoMapper;
import com.dgjs.mapper.admin.AdminUserMapper;
import com.dgjs.mapper.admin.RoleMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.AdminUserInfo;
import com.dgjs.model.persistence.Role;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.condition.RoleAdminCondition;
import com.dgjs.model.persistence.result.AdminUserResult;
import com.dgjs.service.admin.AdminUserService;

@Service
public class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired
	AdminUserInfoMapper adminUserInfoMapper;
	
	@Autowired
	AdminUserMapper adminUserMapper;
	
	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public AdminUserInfo getAdminUserInfo(Integer id) {
		return adminUserInfoMapper.selectById(id);
	}
	
	public AdminUser getAdminUser(Integer id){
		return adminUserMapper.selectById(id);
	}

	@Override
	public int saveOrUpdateAdminUserInfo(AdminUserInfo adminUserInfo) {
		AdminUserInfo o=adminUserInfoMapper.selectById(adminUserInfo.getId());
		if(o==null){
			return adminUserInfoMapper.save(adminUserInfo);
		}else{
			return adminUserInfoMapper.update(adminUserInfo);
		}
	}

	@Override
	public PageInfoDto<AdminUserResult> list(AdminUserCondition condition) {
		condition.setBeginNum((condition.getCurrentPage()-1)*condition.getOnePageSize());
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = adminUserMapper.count(condition);
		}
		List<AdminUserResult> list= adminUserMapper.list(condition);
		setRoleName(list);
		return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, list);
	}

	@Override	
	@CacheEvict(value="cache5m",key="'getByUserCode('+#adminUser.getUser_code()+')'")
	public int updateAdminUser(AdminUser adminUser){
		return adminUserMapper.update(adminUser);
	}

	@Override
	public int update(AdminUser adminUser) {
		return adminUserMapper.update(adminUser);
	}
	
	@Override
//	@Cacheable(value = "cache5m", key = "#root.method.name+'('+#p0+')'")
	@Cacheable(value = "cache5m", key = "'getByUserCode('+#p0+')'")
	public AdminUser getByUserCode(String userCode) {
		return adminUserMapper.getByUserCode(userCode);
	}

	@Override
	public PageInfoDto<AdminUserResult> getAdminByRole(AdminUser adminUser,RoleAdminCondition condition) {
		Role role = roleMapper.selectById(adminUser.getRole_id());
		if(role==null){
			return null;
		}
		List<Integer> roleIds = roleMapper.getChildrenRoleIds(role.getRole_code());
		condition.setRoleIds(roleIds);
		condition.setOrganization(adminUser.getOrganization());
		int totalResults = 0;
		if(condition.isNeedTotalResults()){
			totalResults = adminUserMapper.listByRoleOrgCount(condition);
		}
		List<AdminUserResult> list = adminUserMapper.listByRoleOrg(condition);
		setRoleName(list);
		if(list!=null && !list.isEmpty()){
			return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, list);
		}
		return null;
	}

	@Override
	public List<Integer> getAdminIdsByRole(AdminUser adminUser, RoleAdminCondition condition) {
		Role role = roleMapper.selectById(adminUser.getRole_id());
		if(role==null){
			return null;
		}
		List<Integer> roleIds = roleMapper.getChildrenRoleIds(role.getRole_code());
		condition.setRoleIds(roleIds);
		condition.setOrganization(adminUser.getOrganization());
		int totalResults = adminUserMapper.listByRoleOrgCount(condition);
		condition.setOnePageSize(totalResults);
		List<Integer> list = adminUserMapper.listIdsByRoleOrg(condition);
		return list;
	}

	private void setRoleName(List<AdminUserResult> list){
		if(list!=null&&!list.isEmpty()){
			List<Integer> roleIds = new ArrayList<Integer>();
			for(AdminUserResult aur:list){
				roleIds.add(aur.getRole_id());
			}
			List<Role> roleList=roleMapper.selectByIds(roleIds);
			Map<Integer,Role> map = null;
			if(roleList!=null && !roleList.isEmpty()){
				map = new HashMap<Integer,Role>();
				for(Role role:roleList){
					map.put(role.getId(), role);
				}
				for(AdminUserResult aur:list){
					aur.setRole_name(map.get(aur.getRole_id()).getRole_name());
				}
			}
			
		}
	}

	@Override
	public AdminUser getByUnionId(String unionId) {
		return adminUserMapper.getByUnionId(unionId);
	}

	@Override
	public int save(AdminUser adminUser) {
		return adminUserMapper.save(adminUser);
	}
}
