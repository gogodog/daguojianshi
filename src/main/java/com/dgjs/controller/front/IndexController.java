package com.dgjs.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.Carousel;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.CarouselService;

@Controller
public class IndexController {

	@Autowired
	CarouselService carouselService;
	@Autowired
	ArticlescrapService articlescrapService;
	
	@RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("front/index");
		
		//加载轮播
		Carousel c=new Carousel();
		c.setStatus(UpDown_Status.DOWN);
		List<Carousel> carouselList=carouselService.listCarousel(c);
		mv.addObject("carouselList", carouselList);
		
		//加载推荐文章
		//加载最新文章
		//加载最新评论文章
		
		//加载广告位
        return mv;
    }
	
	@RequestMapping("/show/{id}")
    public ModelAndView show(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("front/show");
		Articlescrap articlescrap= this.articlescrapService.selectById(id);
		mv.addObject("articlescrap", articlescrap);
        return mv;
    }
	
	
}
