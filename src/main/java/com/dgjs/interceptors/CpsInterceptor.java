package com.dgjs.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.Constants;
import com.dgjs.service.admin.NoticeMessageService;
import com.dgjs.utils.WebContextHelper;

public class CpsInterceptor implements HandlerInterceptor{
	
	@Autowired
	NoticeMessageService noticeMessageService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		int unreadCount = noticeMessageService.getUnReadCount(WebContextHelper.getUserId());
		request.setAttribute("unreadCount", unreadCount);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
