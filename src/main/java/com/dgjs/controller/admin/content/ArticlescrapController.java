package com.dgjs.controller.admin.content;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.utils.DateUtils;

@Controller
@RequestMapping("/admin")
public class ArticlescrapController {

	@Autowired
	ArticlescrapService articlescrapSerivce;
	
	@RequestMapping("/articlescrapList")
    public ModelAndView articlescrapList(HttpServletRequest request, HttpServletResponse response,ArticlescrapCondtion condtion) throws Exception {  
		ModelAndView mv = new ModelAndView("admin/content/articlescrap_list");  
		List<Articlescrap> list=articlescrapSerivce.listArticlescrap(condtion);
		mv.addObject("articlescrapList", list);
		return mv;  
    }  
	
	@RequestMapping("/articlescrap")
	public ModelAndView articlescrap(HttpServletRequest request, HttpServletResponse response,Long articlescrapId){
		ModelAndView mv = new ModelAndView("admin/content/articlescrap");  
		if(articlescrapId!=null){
			Articlescrap articlescrap=articlescrapSerivce.selectById(articlescrapId);
			mv.addObject("articlescrap", articlescrap);
		}
		mv.addObject("types", Articlescrap_Type.values());
		return mv;  
	}
	
	@RequestMapping("/saveArticlescrap")
	public ModelAndView saveArticlescrap(Articlescrap articlescrap,String showTime){
		ModelAndView mv = new ModelAndView("redirect:/admin/articlescrapList");  
		articlescrap.setShow_time(DateUtils.parseDateFromString(showTime));
		if(articlescrap.getId()==null){
			articlescrapSerivce.saveArticlescrap(articlescrap);
		}else{
			articlescrapSerivce.updateArticlescrap(articlescrap);
		}
		return mv;
	}
	
	@RequestMapping("/deleteArticlescrap")
	public ModelAndView deleteArticlescrap(Long articlescrapId){
		ModelAndView mv = new ModelAndView("redirect:/admin/articlescrapList");  
		articlescrapSerivce.deleteArticlescrap(articlescrapId);
		return mv;
	}
}
