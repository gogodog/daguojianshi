package com.dgjs.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.enums.Carouse_Status;
import com.dgjs.model.persistence.Carousel;
import com.dgjs.service.content.CarouselService;

@Controller
public class IndexController {

	@Autowired
	CarouselService carouselService;
	
	@RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("front/index");  
		Carousel c=new Carousel();
		c.setStatus(Carouse_Status.DOWN);
		List<Carousel> carouselList=carouselService.listCarousel(c);
		mv.addObject("carouselList", carouselList);
        return mv;
    }
}
