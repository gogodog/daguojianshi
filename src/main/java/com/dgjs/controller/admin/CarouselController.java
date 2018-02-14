package com.dgjs.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.model.enums.Carousel_Position;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Carousel;
import com.dgjs.service.content.CarouselService;

@Controller
@RequestMapping("/admin/cul")
public class CarouselController {
	
	@Autowired
	CarouselService carouselService;
	
	
	@RequestMapping("/carouselList")
    public ModelAndView carouselList(Carousel carousel,HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("admin/content/carousel_list");  
		if(carousel.getPosition()==null){
			carousel.setPosition(Carousel_Position.HISTORY);
		}
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
		mv.addObject("positions", Carousel_Position.values());
		if(carouselId!=null){
			Carousel carousel=carouselService.selectById(carouselId);
			mv.addObject("carousel", carousel);
		}
        return mv;  
	}
	
	@RequestMapping("/saveCarousel")
	@LogRecord(operate=OperateEnum.Add,remark="保存轮播图")
	public ModelAndView saveCarousel(HttpServletRequest request, HttpServletResponse response,Carousel carousel){
		ModelAndView mv = new ModelAndView("redirect:/admin/cul/carouselList?position="+carousel.getPosition());  
		try {
			carouselService.saveOrUpdateCarousel(carousel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return mv;  
	}
	
	@RequestMapping("/deleteCarousel")
	@LogRecord(operate=OperateEnum.Delete,remark="删除轮播图")
	public ModelAndView deleteCarousel(HttpServletRequest request, HttpServletResponse response,Carousel carousel){
		ModelAndView mv = new ModelAndView("redirect:/admin/cul/carouselList?position="+carousel.getPosition());  
		carouselService.deleteById(carousel.getId());
        return mv;  
	}
	
}
