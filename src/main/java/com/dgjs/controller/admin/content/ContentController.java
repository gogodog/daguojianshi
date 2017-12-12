package com.dgjs.controller.admin.content;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.model.enums.OperateEnum;

@Controller
public class ContentController {

	@RequestMapping("/admin")
	@LogRecord(operate=OperateEnum.Browse,remark="admin首页展示")
    public ModelAndView helloWord(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("admin/content/index");  
        return mv;  
    } 
	
}
