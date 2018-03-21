package com.dgjs.service.wechat;

import javax.servlet.http.HttpServletResponse;

public interface LoginService {

	boolean login(String code,String organization, HttpServletResponse response);
	
}
