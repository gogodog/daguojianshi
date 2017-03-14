package com.dgjs.controller.admin.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.persistence.RecommedArticlescrap;
import com.dgjs.model.persistence.condition.RecommedArticlescrapCondition;
import com.dgjs.model.persistence.enhance.RecommedArticlescrapEnhance;
import com.dgjs.service.content.RecommedArticlescrapService;

@Controller
@RequestMapping("/admin")
public class RecommedArticlescrapController {

	@Autowired
	RecommedArticlescrapService recommedArticlescrapService;
	
	@RequestMapping("/recommedArticlescrapList")
	public ModelAndView recommedArticlescrapList(RecommedArticlescrapCondition condition){
		ModelAndView mv = new ModelAndView("admin/content/recommedArticlescrap_list");  
		List<RecommedArticlescrapEnhance> list=recommedArticlescrapService.list(condition);
		mv.addObject("recommedArticlescrapList", list);
		return mv;
	}
	
	@RequestMapping("/recommedArticlescrap")
	public ModelAndView recommedArticlescrap(){
		ModelAndView mv = new ModelAndView("admin/content/recommedArticlescrap");  
		return mv;
	}
	
	@RequestMapping("/saveRecommedArticlescrap")
	public ModelAndView saveRecommedArticlescrap(RecommedArticlescrap recommedArticlescrap){
		ModelAndView mv = new ModelAndView("redirect:/admin/recommedArticlescrapList"); 
		recommedArticlescrapService.save(recommedArticlescrap);
		return mv;
	}
	
	@RequestMapping("/deleteRecommedArticlescrap")
	public ModelAndView deleteRecommedArticlescrap(Long recommedArticlescrapId){
		ModelAndView mv = new ModelAndView("redirect:/admin/recommedArticlescrapList"); 
		recommedArticlescrapService.deleteById(recommedArticlescrapId);
		return mv;
	}
	
	@RequestMapping("/updateStatus")
	public String updateStatus(Long recommedArticlescrapId){
		return null;
	}
}
