package com.dgjs.controller.admin.content;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.utils.DateUtils;

@Controller
@RequestMapping("/admin")
public class ArticlescrapController {
	
	@Autowired
	ArticlescrapService articlescrapSerivce;
	
	@Autowired
	PictureService pictureService;
	
	@RequestMapping("/articlescrapList")
    public ModelAndView articlescrapList(HttpServletRequest request, HttpServletResponse response,ArticlescrapCondtion condtion) throws Exception {  
		ModelAndView mv = new ModelAndView("admin/content/articlescrap_list");
		condtion.setNeedTotalResults(true);
		PageInfoDto<Articlescrap> pageInfo=articlescrapSerivce.listArticlescrap(condtion);
		mv.addObject("pageInfo", pageInfo);
		mv.addObject("condition",condtion);
		mv.addObject("articlescrapTypes", Articlescrap_Type.values());
		mv.addObject("upDownStatus", UpDown_Status.values());
		return mv;  
    }  
	
	@RequestMapping("/articlescrap")
	public ModelAndView articlescrap(String articlescrapId) throws Exception{
		ModelAndView mv = new ModelAndView("admin/content/articlescrap");  
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		if(articlescrapId!=null){
			Articlescrap articlescrap=articlescrapSerivce.selectById(articlescrapId);
			mv.addObject("articlescrap", articlescrap);
		}
		mv.addObject("types", Articlescrap_Type.values());
		return mv;  
	}
	
	@RequestMapping("/saveArticlescrap")
	public ModelAndView saveArticlescrap(Articlescrap articlescrap,String showTime) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/articlescrapList");  
		articlescrap.setShow_time(DateUtils.parseDateFromString(showTime));
		if(StringUtils.isEmpty(articlescrap.getId()))
			articlescrapSerivce.saveArticlescrap(articlescrap);
		else
			articlescrapSerivce.updateArticlescrap(articlescrap);
		return mv;
	}
	
	@RequestMapping("/deleteArticlescrap")
	public ModelAndView deleteArticlescrap(String articlescrapId)  throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/articlescrapList");  
		articlescrapSerivce.deleteArticlescrap(articlescrapId);
		return mv;
	}
	
	@RequestMapping("/previewArticlescrap")
	public ModelAndView previewArticlescrap(String articlescrapId)  throws Exception{
		ModelAndView mv = articlescrap(articlescrapId);
		mv.setViewName("front/show");
		return mv;
	}
}
