package com.dgjs.controller.admin.content;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class ContentController {

	@RequestMapping("/contentList")
    public ModelAndView helloWord(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("admin/content/index");  
        mv.addObject("title", "Spring MVC And Freemarker");  
        mv.addObject("content", " Hello world ， test my first spring mvc ! ");  
        return mv;  
    }  
}
