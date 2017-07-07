package com.dgjs.controller.front;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Ad_Position;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Advertisement;
import com.dgjs.model.persistence.Carousel;
import com.dgjs.model.persistence.condition.AdvertisementCondtion;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.ad.AdvertisementService;
import com.dgjs.service.common.DataService;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.CarouselService;
import com.dgjs.service.content.CommentsService;
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
	CommentsService commentsService;
	@Autowired
	PictureService pictureService;
	@Autowired
	DataService dataSerivce;
	
	@RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response,Articlescrap_Type type,String keyword) throws Exception {  
		ModelAndView mv = new ModelAndView("front/index");
		//加载轮播
		Carousel c=new Carousel();
		c.setStatus(UpDown_Status.UP);
		List<Carousel> carouselList=carouselService.listCarousel(c);
		mv.addObject("carouselList", carouselList);
		//加载推荐文章
		List<Articlescrap> rAEList=recommedArticlescrapService.list(UpDown_Status.UP);
		mv.addObject("rAEList", rAEList);
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		//加载广告位
		AdvertisementCondtion advertisementCondtion = new AdvertisementCondtion();
		advertisementCondtion.setAdPositions(Arrays.asList(Ad_Position.INDEX_FIRST,Ad_Position.INDEX_SECOND));
		advertisementCondtion.setStatus(UpDown_Status.UP);
		PageInfoDto<Advertisement> adPicPageInfo=advertisementService.listAdvertisement(advertisementCondtion);
		List<Advertisement> adPicList=adPicPageInfo.getObjects();
		mv.addObject("adPicList", adPicList);
		//中间广告位
		advertisementCondtion.setAdPositions(Ad_Position.getValues(131, 135));
		PageInfoDto<Advertisement> adMiddlePageInfo=advertisementService.listAdvertisement(advertisementCondtion);
		List<Advertisement> adMiddleList=adMiddlePageInfo.getObjects();
		mv.addObject("adMiddleList", adMiddleList);
		//底部广告位
		advertisementCondtion.setAdPositions(Ad_Position.getValues(161, 163));
		PageInfoDto<Advertisement> adBelowPageInfo=advertisementService.listAdvertisement(advertisementCondtion);
		List<Advertisement> adBelowList=adBelowPageInfo.getObjects();
		mv.addObject("adBelowList", adBelowList);
		//加载最新评论文章
		List<Articlescrap> commentsArticlescrapList=articlescrapService.getArticlescrapByComments(2);
		mv.addObject("commentsArticlescrapList", commentsArticlescrapList);
		//加载分类
		mv.addObject("types", Articlescrap_Type.values());
		//加载页面类型
		mv.addObject("doctype",type);
		//加载搜索条件
		mv.addObject("keyword", keyword);
		//加载打点初始化数据
		List<Advertisement> totalAd=new ArrayList<Advertisement>();
		totalAd.addAll(adPicList);
		totalAd.addAll(adMiddleList);
		totalAd.addAll(adBelowList);
		String pageadids=advertisementService.getDadianAdvertisementIds(totalAd);
		mv.addObject("pageadids", pageadids);
		return mv;
    }
	
	@RequestMapping("/list")
	@ResponseBody
    public Object list(HttpServletRequest request, HttpServletResponse response,Articlescrap_Type type,int currentpage,String keyword) throws Exception {  
		JSONObject list = new JSONObject();
		//加载最新文章
		ArticlescrapCondtion articlescrapCondtion = new ArticlescrapCondtion();
		articlescrapCondtion.setNeedTotalResults(false);
		articlescrapCondtion.setStatus(UpDown_Status.UP);
		articlescrapCondtion.setType(type);
		articlescrapCondtion.setCurrentPage(currentpage);
		articlescrapCondtion.setKeyword(keyword);
//		articlescrapCondtion.setOnePageSize(2);//默认10条
		Map<String,SortOrder> sort = new HashMap<String,SortOrder>();
		sort.put("show_time", SortOrder.DESC);
		articlescrapCondtion.setSort(sort);
		PageInfoDto<Articlescrap> pageInfo=articlescrapService.listArticlescrap(articlescrapCondtion);
		list.put("pageInfo", pageInfo);
		list.put("imageContextPath", pictureService.getImageContextPath());
		//加载文章阅读量
		List<Articlescrap> aticlescrapList = pageInfo == null?null:pageInfo.getObjects();
		if(aticlescrapList!=null&&aticlescrapList.size()>0){
			List<String> articlescrapIds = new ArrayList<String>();
			for(Articlescrap articlescrap:aticlescrapList){
				articlescrapIds.add(String.valueOf(articlescrap.getId()));
			}
			Map<String,Integer> map=dataSerivce.getDocShowCounts(articlescrapIds);
			list.put("visits", map);
		}
		return list;
    }
	
	@RequestMapping("/error")
    public ModelAndView error(int e){  
		ModelAndView mv = new ModelAndView("front/error");
        return mv;
    }
	
}
