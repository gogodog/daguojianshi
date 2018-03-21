package com.dgjs.service.transaction;

import javax.servlet.http.HttpServletResponse;

import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.Organization;
import com.dgjs.model.wechat.res.UserInfo;

public interface AdminUserTransactionService {

	public AdminUser wxLogin(UserInfo userInfo,String organization,HttpServletResponse response);
	
	public void saveOrganization(Organization organization);
	
	public void updateOrganization(Organization organization);
}
