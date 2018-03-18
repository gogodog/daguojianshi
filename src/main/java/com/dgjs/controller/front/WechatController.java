package com.dgjs.controller.front;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgjs.service.wechat.WechatService;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	@Autowired
	WechatService wechatService;
	
	@ResponseBody
	@RequestMapping("/getWechatInfo")
	public Object getWechatInfo(String url){
		Map<String,Object> map = wechatService.getBaseConfig(url);
		return map;
	}
}
