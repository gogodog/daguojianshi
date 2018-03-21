package com.dgjs.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.service.wechat.LoginService;
import com.dgjs.utils.WebContextHelper;

@Controller
public class ALoginController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping("/adminlogin")
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView("/admin/login/login");
		return mv;
	}
	
	@RequestMapping("/admin/logout")
	public ModelAndView logout(){
		ModelAndView mv = new ModelAndView("/admin/login/login");
		WebContextHelper.cleanSession();
		return mv;
	}
	
	@RequestMapping("/admin/ck")
	public ModelAndView loginCallBack(String code, String state,String source,HttpServletRequest request,HttpServletResponse response) {
		boolean isLogin = loginService.login(code,source,response);
		ModelAndView mv = null;
		if(!isLogin){//获取用户信息失败
			mv = new ModelAndView("/admin/login/login");
			mv.addObject("errcode", "901");
		}else{
			//登录成功应该重定向cps的首页
		    mv = new ModelAndView("redirect:/admin"); 
		}
		return mv;
	}
}
