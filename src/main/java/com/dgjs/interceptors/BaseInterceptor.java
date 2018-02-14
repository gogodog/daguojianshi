package com.dgjs.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class BaseInterceptor implements HandlerInterceptor{
	
    @Value("${staticVersion}")
	private String staticVersion;
    
    @Value("${defaultImage}")
    private String defaultImage;
    
    @Value("${imageContextPath}")
    private String imageContextPath;
    
    @Value("${fastFDSContextPath}")
    private String fastFDSContextPath;
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("contextPath", request.getContextPath());
		request.setAttribute("staticVersion", staticVersion);
		request.setAttribute("defaultImage", defaultImage);
		request.setAttribute("imageContextPath", imageContextPath);
		request.setAttribute("fastFDSContextPath", fastFDSContextPath);
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
