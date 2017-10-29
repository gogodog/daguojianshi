package com.cps.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
public class XController {

	@RequestMapping("/test")
	public void init(HttpServletRequest request,HttpServletResponse response) {  
	    List<String> uList = new ArrayList<String>();//存储所有url集合    
	    WebApplicationContext wac = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);//获取上下文对象  
	    Map<String, HandlerMapping> requestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(wac, HandlerMapping.class, true, false);  
	    for(HandlerMapping handlerMapping : requestMappings.values()) {  
	        if(handlerMapping instanceof RequestMappingHandlerMapping) {  
	            RequestMappingHandlerMapping rmhm = (RequestMappingHandlerMapping) handlerMapping;  
	            Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods(); 
	            for(RequestMappingInfo rmi : handlerMethods.keySet()) { 
	                PatternsRequestCondition prc = rmi.getPatternsCondition();  
	                Set<String> patterns = prc.getPatterns();  
	                for (String uStr : patterns)  {
	                	 uList.add(uStr);  
	                	 System.out.println(uStr);
	                }
	                   
	            }  
	        }  
	    }  
//	    System.out.println(uList);
	}  
}
