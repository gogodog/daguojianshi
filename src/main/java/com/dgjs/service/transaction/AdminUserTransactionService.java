package com.dgjs.service.transaction;

import javax.servlet.http.HttpServletResponse;

import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.wechat.res.UserInfo;

public interface AdminUserTransactionService {

	public AdminUser wxLogin(UserInfo userInfo,HttpServletResponse response);
	
}
