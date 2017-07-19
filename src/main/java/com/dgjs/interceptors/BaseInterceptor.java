package com.dgjs.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.utils.MachineUtils;

import freemarker.log.Logger;

public class BaseInterceptor implements HandlerInterceptor{
	private Logger log = Logger.getLogger(this.getClass().getName());
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("contextPath", request.getContextPath());
		String userAgent = request.getHeader("USER-AGENT");  
		log.info("http:--" + request.getRequestURL());
        boolean isphone =MachineUtils.check(userAgent);  
        if(isphone){  
        	log.info("我是手机...");
        }else{
        	log.info("我是电脑...");
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
