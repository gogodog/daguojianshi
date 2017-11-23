package com.dgjs.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dgjs.constants.Session_Keys;
import com.dgjs.model.persistence.AdminUser;

public class WebContextHelper {

	private static HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
		HttpServletRequest request = attributes.getRequest();
        return request;
    }

	public static AdminUser getAdminUser(){
		AdminUser adminUser = (AdminUser) getRequest().getSession().getAttribute(Session_Keys.USER_INFO);
		return adminUser;
	}
	
	public static void setSessionValue(String name,Object value){
		getRequest().getSession().setAttribute(name, value);
	}
	
	public static Integer getUserId(){
		return getAdminUser()==null?null:getAdminUser().getId();
	}
	
	public static String getUserCode(){
		return getAdminUser()==null?null:getAdminUser().getUser_code();
	}
	
}
