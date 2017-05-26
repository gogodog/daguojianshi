package com.dgjs.controller.common.shuju;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgjs.service.common.DataService;

@Controller
@RequestMapping("/data")
public class DataController {
	
	@Autowired
	DataService dataSerivce;
	
	@RequestMapping(value="/dadian",method=RequestMethod.POST)
	@ResponseBody
    public boolean dadian(HttpServletRequest request, @RequestBody String dadian) throws Exception {
		return dataSerivce.dadian(request, dadian);  
    }  
}
