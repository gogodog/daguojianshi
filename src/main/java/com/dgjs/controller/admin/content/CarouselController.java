package com.dgjs.controller.admin.content;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.persistence.Carousel;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.CarouselService;

@Controller
@RequestMapping("/admin")
public class CarouselController {
	
	@Autowired
	CarouselService carouselService;
	
	@Autowired
	PictureService pictureService;
	
	
	@RequestMapping("/carouselList")
    public ModelAndView carouselList(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("admin/content/carousel_list");  
		List<Carousel> carouselList=carouselService.listCarousel(null);
		mv.addObject("carouselList", carouselList);
        return mv;  
    }  
	
	@RequestMapping("/carousel")
	public ModelAndView carousel(HttpServletRequest request, HttpServletResponse response,Long carouselId){
		ModelAndView mv = new ModelAndView("admin/content/carousel"); 
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		if(carouselId!=null){
			Carousel carousel=carouselService.selectById(carouselId);
			mv.addObject("carousel", carousel);
		}
        return mv;  
	}
	
	@RequestMapping("/saveCarousel")
	public ModelAndView saveCarousel(HttpServletRequest request, HttpServletResponse response,Carousel carousel){
		ModelAndView mv = new ModelAndView("redirect:/admin/carouselList");  
		try {
			carouselService.saveOrUpdateCarousel(carousel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return mv;  
	}
	
	@RequestMapping("/deleteCarousel")
	public ModelAndView deleteCarousel(HttpServletRequest request, HttpServletResponse response,Long carouselId){
		ModelAndView mv = new ModelAndView("redirect:/admin/carouselList");  
		carouselService.deleteById(carouselId);
        return mv;  
	}
	
}
