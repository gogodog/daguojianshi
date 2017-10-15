package com.dgjs.service.admin;

import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.AdminUserInfo;

public interface AdminUserService {

	public AdminUserInfo getAdminUserInfo(Integer id);
	
	public AdminUser getAdminUser(Integer id);
	
	public int saveOrUpdateAdminUserInfo(AdminUserInfo adminUserInfo);
}
