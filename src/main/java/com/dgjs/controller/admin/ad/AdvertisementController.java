package com.dgjs.controller.admin.ad;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Ad_Position;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Advertisement;
import com.dgjs.model.persistence.condition.AdvertisementCondtion;
import com.dgjs.service.ad.AdvertisementService;
import com.dgjs.service.common.PictureService;

@Controller
@RequestMapping("/admin")
public class AdvertisementController {

	@Autowired
	AdvertisementService advertisementService;
	
	@Autowired
	PictureService pictureService;
	
	@RequestMapping("/adList")
	public ModelAndView adList(AdvertisementCondtion condition){
		ModelAndView mv = new ModelAndView("admin/ad/advertisement_list");
		condition.setAdPositions(condition.getAdPosition()==null?null:Arrays.asList(condition.getAdPosition()));
		condition.setNeedTotalResults(true);
		PageInfoDto<Advertisement> pageInfo=advertisementService.listAdvertisement(condition);
		mv.addObject("pageInfo", pageInfo);
		mv.addObject("condition",condition);
		mv.addObject("upDownStatus", UpDown_Status.values());
		mv.addObject("adPositions", Ad_Position.values());
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		return mv;
	}
	
	@RequestMapping("/ad")
	public ModelAndView ad(Long adId){
		ModelAndView mv = new ModelAndView("admin/ad/advertisement");
		Advertisement advertisement=advertisementService.selectById(adId);
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		mv.addObject("advertisement", advertisement);
		mv.addObject("adPositions", Ad_Position.values());
		return mv;
	}
	
	@RequestMapping("/saveAdvertisement")
	public ModelAndView saveAdvertisement(Advertisement advertisement){
		ModelAndView mv = new ModelAndView("redirect:/admin/adList");  
		if(advertisement.getId()!=null){
			advertisementService.updateAdvertisement(advertisement);
		}else{
			advertisementService.saveAdvertisement(advertisement);
		}
		return mv;
	}
	
	@RequestMapping("/deleteAdvertisement")
	public ModelAndView deleteAdvertisement(Long adId){
		ModelAndView mv = new ModelAndView("redirect:/admin/adList");  
		advertisementService.deleteById(adId);
		return mv;
	}
}
