package com.dgjs.mapper.admin;

import com.dgjs.model.persistence.AdminUserInfo;

public interface AdminUserInfoMapper {

	public int save(AdminUserInfo adminUserInfo);
	
	public AdminUserInfo selectById(Long id);
	
	public int update(AdminUserInfo adminUserInfo);
	
}
