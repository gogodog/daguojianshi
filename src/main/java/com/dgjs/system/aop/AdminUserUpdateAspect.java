package com.dgjs.system.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dgjs.constants.Session_Keys;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.utils.WebContextHelper;

@Component
@Aspect
public class AdminUserUpdateAspect {

	public final static String ADMINUSERUPDATE="execution(public * com.dgjs.service.admin.AdminUserService.updateAdminUser(..))";

	@Autowired
	AdminUserService adminUserService;
	
	@Around(ADMINUSERUPDATE)
	public Object proceed(ProceedingJoinPoint point) throws Throwable{
		Object[] args = point.getArgs();
		AdminUser adminUser = (AdminUser) args[0];
		AdminUser originAdminUser = adminUserService.getAdminUser(adminUser.getId());
		adminUser.setUser_code(originAdminUser.getUser_code());
		args[0] = adminUser;
		Object result = point.proceed();
		int r = (int) result;
		if(r>0)
		   WebContextHelper.setSessionValue(Session_Keys.USER_INFO, adminUser);
		return result;
	}
	
}
