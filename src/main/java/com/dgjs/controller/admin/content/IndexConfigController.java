package com.dgjs.controller.admin.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.dto.IndexConfigDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Index_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.IndexConfig;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.IndexConfigService;

@Controller
@RequestMapping("/admin/idxcfg")
public class IndexConfigController {

	@Autowired
	private IndexConfigService indexConfigService;
	@Autowired
	ArticlescrapService articlescrapService;
	
	@RequestMapping("/list")
	public ModelAndView list(IndexConfig config){
		ModelAndView mv = new ModelAndView("admin/content/m_ic_list");
        if(config.getType()==null){
			config.setType(Index_Type.HISTORY);
		}
		List<IndexConfigDto> list=indexConfigService.list(config);
		mv.addObject("list", list);
		mv.addObject("types", Index_Type.values());
		mv.addObject("config",config);
		mv.addObject("upDownStatus",UpDown_Status.values());
		return mv;
	}
	
	@RequestMapping("/indexConfig")
	public ModelAndView indexConfig(Long id){
		ModelAndView mv = new ModelAndView("admin/content/m_ic");
		if(id!=null){
			IndexConfigDto dto = indexConfigService.selectById(id);
			mv.addObject("config", dto);
		}
		mv.addObject("types", Index_Type.values());
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(IndexConfig config){
		ModelAndView mv = new ModelAndView("redirect:/admin/idxcfg/list?type="+config.getType());  
		indexConfigService.delete(config.getId());
		return mv;
	}
	
	@RequestMapping("/saveOrUpdate")
	public ModelAndView update(IndexConfig config,String[] pics){
		ModelAndView mv = new ModelAndView("redirect:/admin/idxcfg/list?type="+config.getType()); 
		String [] ids={config.getAid()};
		List<Articlescrap> list=articlescrapService.getArticlescrapByIds(ids);
		if(list == null || list.size() == 0){
			return mv;
		}
		if(pics!=null && pics.length>0){
			StringBuilder picture = new StringBuilder();
			for(int i=0;i<pics.length;i++){
				picture.append(pics[i]);
				if(i!=pics.length-1){
					picture.append(",");
				}
			}
			config.setPictures(picture.toString());
		}
		if(config.getId()!=null){
			indexConfigService.update(config);
		}else{
			indexConfigService.save(config);
		}
		return mv;
	}
}
