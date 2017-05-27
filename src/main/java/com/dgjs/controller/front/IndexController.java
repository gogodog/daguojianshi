package com.dgjs.controller.front;

import java.security.AccessControlException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Ad_Position;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Advertisement;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.Carousel;
import com.dgjs.model.persistence.condition.AdvertisementCondtion;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.model.persistence.condition.RecommedArticlescrapCondition;
import com.dgjs.model.persistence.enhance.RecommedArticlescrapEnhance;
import com.dgjs.service.ad.AdvertisementService;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.CarouselService;
import com.dgjs.service.content.RecommedArticlescrapService;

@Controller
public class IndexController {

	@Autowired
	CarouselService carouselService;
	@Autowired
	ArticlescrapService articlescrapService;
	@Autowired
	RecommedArticlescrapService recommedArticlescrapService;
	@Autowired
	AdvertisementService advertisementService;
	
	@Autowired
	PictureService pictureService;
	
	@RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("front/index");
		//加载轮播
		Carousel c=new Carousel();
		c.setStatus(UpDown_Status.UP);
		List<Carousel> carouselList=carouselService.listCarousel(c);
		mv.addObject("carouselList", carouselList);
		//加载推荐文章
		RecommedArticlescrapCondition r=new RecommedArticlescrapCondition();
		List<RecommedArticlescrapEnhance> rAEList=recommedArticlescrapService.list(r);
		mv.addObject("rAEList", rAEList);
		//加载最新文章
		ArticlescrapCondtion articlescrapCondtion = new ArticlescrapCondtion();
		articlescrapCondtion.setOnePageSize(5);
		articlescrapCondtion.setNeedTotalResults(false);
		articlescrapCondtion.setStatus(UpDown_Status.UP);
		articlescrapCondtion.setSort(" order by show_time desc ");
		PageInfoDto<Articlescrap> pageInfo=articlescrapService.listArticlescrap(articlescrapCondtion);
		mv.addObject("articlescrapPageInfo", pageInfo);
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		//加载广告位
		AdvertisementCondtion advertisementCondtion = new AdvertisementCondtion();
		advertisementCondtion.setAdPositions(Arrays.asList(Ad_Position.INDEX_FIRST,Ad_Position.INDEX_SECOND));
		advertisementCondtion.setStatus(UpDown_Status.UP);
		PageInfoDto<Advertisement> advertisementPageInfo=advertisementService.listAdvertisement(advertisementCondtion);
		List<Advertisement> advertisementList=advertisementPageInfo.getObjects();
		mv.addObject("advertisementList", advertisementList);
		//加载最新评论文章
        return mv;
    }
	
	@RequestMapping("/error")
    public ModelAndView error(int e){  
		ModelAndView mv = new ModelAndView("front/error");
        return mv;
    }
	
	@RequestMapping("/show/{id}")
    public ModelAndView show(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("front/show");
		Articlescrap articlescrap= this.articlescrapService.selectById(id);
		mv.addObject("articlescrap", articlescrap);
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
        return mv;
    }
	
	
}
