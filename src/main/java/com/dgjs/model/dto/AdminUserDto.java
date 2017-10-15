package com.dgjs.model.dto;

import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.AdminUserInfo;

public class AdminUserDto {

	private AdminUser adminUser;
	
	private AdminUserInfo adminUserInfo;

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public AdminUserInfo getAdminUserInfo() {
		return adminUserInfo;
	}

	public void setAdminUserInfo(AdminUserInfo adminUserInfo) {
		this.adminUserInfo = adminUserInfo;
	}
	
	
}
