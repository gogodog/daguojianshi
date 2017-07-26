package com.dgjs.controller.admin.content;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.enums.Carousel_Position;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Carousel;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.CarouselService;

@Controller
@RequestMapping("/admin/cul")
public class CarouselController {
	
	@Autowired
	CarouselService carouselService;
	
	@Autowired
	PictureService pictureService;
	
	
	@RequestMapping("/carouselList")
    public ModelAndView carouselList(Carousel carousel,HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("admin/content/carousel_list");  
		List<Carousel> carouselList=carouselService.listCarousel(carousel);
		mv.addObject("carouselList", carouselList);
		mv.addObject("upDownStatus", UpDown_Status.values());
		mv.addObject("positions", Carousel_Position.values());
		mv.addObject("carousel", carousel);
        return mv;  
    }  
	
	@RequestMapping("/carousel")
	public ModelAndView carousel(HttpServletRequest request, HttpServletResponse response,Long carouselId){
		ModelAndView mv = new ModelAndView("admin/content/carousel"); 
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		mv.addObject("positions", Carousel_Position.values());
		if(carouselId!=null){
			Carousel carousel=carouselService.selectById(carouselId);
			mv.addObject("carousel", carousel);
		}
        return mv;  
	}
	
	@RequestMapping("/saveCarousel")
	public ModelAndView saveCarousel(HttpServletRequest request, HttpServletResponse response,Carousel carousel){
		ModelAndView mv = new ModelAndView("redirect:/admin/cul/carouselList");  
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
		ModelAndView mv = new ModelAndView("redirect:/admin/cul/carouselList");  
		carouselService.deleteById(carouselId);
        return mv;  
	}
	
}
