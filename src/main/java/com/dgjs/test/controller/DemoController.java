package com.dgjs.test.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dgjs.test.dao.SysRightDao;

@Controller
@RequestMapping("/test")
public class DemoController {
	
	@Resource
	SysRightDao sysRightDao;
	
	@RequestMapping("/go")
	public String go(HttpServletRequest request,Model model){
		List<Map<String,Object>> list = sysRightDao.selectqxByUUID("");
		model.addAttribute("list", list.get(0).toString());
		return "test";
	}
}
