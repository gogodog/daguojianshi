package com.dgjs.service.admin;


import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.AdminUserInfo;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.result.AdminUserResult;

public interface AdminUserService {

	public AdminUserInfo getAdminUserInfo(Integer id);
	
	public AdminUser getAdminUser(Integer id);
	
	public int saveOrUpdateAdminUserInfo(AdminUserInfo adminUserInfo);
	
	public PageInfoDto<AdminUserResult> list(AdminUserCondition condition);
	
	public int updateAdminUser(AdminUser adminUser);
	
	public AdminUser getByUserCode(String userCode);

}
