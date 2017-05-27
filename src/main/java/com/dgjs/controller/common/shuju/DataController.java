package com.dgjs.controller.common.shuju;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
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
    public boolean dadian(HttpServletRequest request, @RequestBody String dadian){
		log.setCategoryPrefix("china-大国简史");
		log.info("info::");
		log.error("error::");
		log.debug("debug::");
		return dataSerivce.dadian(request, dadian);  
    }
	
	@RequestMapping(value="/getdocshow",method=RequestMethod.GET)
	@ResponseBody
    public JSONObject getDocShowCounts(String docids){
		return dataSerivce.getDocShowCounts(docids);
    }
}
