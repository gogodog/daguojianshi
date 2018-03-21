package com.dgjs.service.admin;


import java.util.List;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.AdminUserInfo;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.condition.RoleAdminCondition;
import com.dgjs.model.persistence.result.AdminUserResult;

public interface AdminUserService {

	public AdminUserInfo getAdminUserInfo(Integer id);
	
	public AdminUser getAdminUser(Integer id);
	
	public int saveOrUpdateAdminUserInfo(AdminUserInfo adminUserInfo);
	
	public PageInfoDto<AdminUserResult> list(AdminUserCondition condition);
	
	public int updateAdminUser(AdminUser adminUser);
	
	public int update(AdminUser adminUser);
	
	public AdminUser getByUserCode(String userCode);
	
	public PageInfoDto<AdminUserResult> getAdminByRole(AdminUser adminUser,RoleAdminCondition condition);
	
	public List<Integer> getAdminIdsByRole(AdminUser adminUser,RoleAdminCondition condition);
	
	public AdminUser getByUnionId(String unionId);
	
	public int save(AdminUser adminUser);
}
