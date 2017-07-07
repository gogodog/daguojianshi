package com.dgjs.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dgjs.utils.IPUtils;

import freemarker.log.Logger;

public class BaseInterceptor implements HandlerInterceptor{

	
	private Logger log = Logger.getLogger(this.getClass().getName()); 
	
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("contextPath", request.getContextPath());
		if(request.getRequestURI().contains("phpMyAdmin")){
			String ua = request.getHeader("User-Agent");
			String ip = IPUtils.getIpAddr3(request);
			log.error("phpMyAdmin exception,ua=["+JSON.toJSONString(ua)+"],ip=["+ip+"]");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
