package com.dgjs.mapper.admin;

import java.util.List;

import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.condition.AdminUserCondition;

public interface AdminUserMapper {

	public int batchSave(List<AdminUser> list);
	
	public int save(AdminUser adminUser);
	
	public AdminUser selectById(Long id);
	
	public int update(AdminUser adminUser);
	
	public List<AdminUser> list(AdminUserCondition condition);
}
