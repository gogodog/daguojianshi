package com.dgjs.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.view.ErrorView;

import freemarker.log.Logger;

public class ExceptionOperation implements HandlerExceptionResolver{

	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest r, HttpServletResponse p, Object o, Exception ex) {
		ModelAndView mv = new ModelAndView("front/error");
        mv.addObject("ex", ex);
        ErrorView ev = new ErrorView();
        
        log.error("异常 信息：" + ex.getMessage());
        log.error("异常 上线文：" + r.getContextPath());
        log.error("异常 本地地址：" + r.getLocalAddr());
        if(ex instanceof NullPointerException) {
        	ev.setErrorCode("800021");
        	ev.setErrorMessage(ex.getMessage());
        	ev.setProblem("空指针异常");
        	ev.setTips("系统空指针找不到你要的页面");
        	ev.setCallback("/test/freemaker?page=index");
        }else {
        	ev.setErrorCode("800022");
        	ev.setErrorMessage(ex.getMessage());
        	ev.setProblem("系统异常");
        	ev.setCallback("/test/freemaker?page=index");
        	ev.setTips("系统异常 系统发出异常信息请检查你的操作");
        }
        mv.addObject("info", ev);
        return mv;
	}
}
