package com.dgjs.controller.common.shuju;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.view.DadianView;
import com.dgjs.service.common.DataService;

import freemarker.log.Logger;

@Controller
@RequestMapping("/data")
public class DataController {
	
	@Autowired
	DataService dataSerivce;
	private Logger log = Logger.getLogger(this.getClass().getName()); 
	
	@RequestMapping(value="/dadian",method=RequestMethod.POST)
	@ResponseBody
    public String dadian(HttpServletRequest request, @RequestBody String dadian){
		log.info("进入首页");
		DadianView dadianView = JSON.parseObject(dadian, DadianView.class);
	    dataSerivce.dadian(request, dadianView); 
	    ModelAndView mv = new ModelAndView();
	    //首页访问量
	    int indexVisitCount = dataSerivce.getPageTotalVisits("10336266");
	    mv.addObject("indexVisitCount",indexVisitCount);
	    return JSON.toJSONString(mv.getModel());
    }
	
}
