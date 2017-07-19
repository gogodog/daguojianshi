package com.dgjs.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.utils.MachineUtils;

public class BaseInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("contextPath", request.getContextPath());
		String userAgent = request.getHeader("USER-AGENT");  
        boolean isphone =MachineUtils.check(userAgent);  
        if(isphone){  
            //手机访问操作
        	System.out.println("我是手机...");
        }else{
        	//pc访问操作
        	System.out.println("我是电脑...");
        }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
	}

}
