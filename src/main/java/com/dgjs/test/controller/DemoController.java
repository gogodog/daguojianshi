package com.dgjs.test.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dgjs.test.dao.SysRightDao;

@Controller
@RequestMapping("/test")
public class DemoController {
	
	@Resource
	SysRightDao sysRightDao;
	
	@RequestMapping("/url")
	public String url(HttpServletRequest request,Model model){
		model.addAttribute("data", "200");
		model.addAttribute("msg", "so amazing ");
		return "test";
	}
	
	@RequestMapping("/ajax")
	@ResponseBody
	public Object ajax(String msg){
		JSONObject jso = new JSONObject();
		jso.put("data", 200);
		jso.put("msg", "ajax ok");
		jso.put("页面带过来的参数", msg);
		return jso;
	}
	
	@RequestMapping("/db")
	@ResponseBody
	public Object db(String msg){
		JSONObject jso = new JSONObject();
		jso.put("data", 200);
		jso.put("msg", "db ok");
		List<Map<String,Object>> list = sysRightDao.selectqxByUUID(msg);
		jso.put("dbResult", Arrays.toString(list.toArray()));
		jso.put("页面带过来的参数", msg);
		return jso;
	}
}
