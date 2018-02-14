package com.dgjs.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.persistence.Config;
import com.dgjs.model.persistence.condition.ConfigCondition;
import com.dgjs.service.config.ConfigService;

@Controller
@RequestMapping("/admin/config")
public class ConfigController {

	@Autowired
	ConfigService configService;
	
	@RequestMapping("/list")
	public ModelAndView list(ConfigCondition condition){
		ModelAndView mv = new ModelAndView("admin/config/configs");
		List<Config> list = configService.list(condition);
		mv.addObject("list", list);
		mv.addObject("condition", condition);
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(Integer id){
		ModelAndView mv = new ModelAndView("redirect:/admin/config/list"); 
		configService.deleteById(id);
		return mv;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(Integer id){
		ModelAndView mv = new ModelAndView("/admin/config/config"); 
		Config config = configService.selectById(id);
		mv.addObject("config", config);
		return mv;
	}
	
	
	@RequestMapping("/save")
	public ModelAndView save(Config config){
		ModelAndView mv = new ModelAndView("redirect:/admin/config/list"); 
		if(StringUtils.isEmpty(config.getC_key())||StringUtils.isEmpty(config.getC_desc())||StringUtils.isEmpty(config.getC_value())){
		}else{
			configService.save(config);
		}
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(Config config){
		ModelAndView mv = new ModelAndView("redirect:/admin/config/list"); 
		if(StringUtils.isEmpty(config.getC_desc())||StringUtils.isEmpty(config.getC_value())){
		}else{
			Config origin = configService.selectById(config.getId());
			if(origin!=null){
				origin.setC_desc(config.getC_desc());
				origin.setC_value(config.getC_value());
				configService.update(origin);
			}
		}
		return mv;
	}
}
