package com.dgjs.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dgjs.utils.MachineUtils;

import freemarker.log.Logger;

@Controller
public class RedirectController {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping("/dgjs")
    public String index(HttpServletRequest request) throws Exception {  
        boolean isphone =MachineUtils.check(request.getHeader("USER-AGENT"));  
        log.info((isphone?"[mobile]":"[pc]") + request.getRequestURL()+"");
        if(isphone){  
        	return "forward:/m/index";
        }else{
        	return "forward:/index";
        }
    }
}
