package com.dgjs.controller.admin.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.persistence.Channel;
import com.dgjs.service.content.ChannelService;

@Controller
@RequestMapping("/admin/channel")
public class ChannelController {

	@Autowired
	ChannelService channelService;
	
	@RequestMapping("/save")
	public ModelAndView saveChannel(Channel channel){
		ModelAndView mv = new ModelAndView("redirect:/admin/channel/list"); 
		if(channel.getC_name()==null||channel.getC_name().length()>10){
			return mv;
		}else if(channel.getStatus()==null){
			return mv;
		}
		channelService.save(channel);
		return mv;
	}
	
	@RequestMapping("/list")
	public ModelAndView listChannel(){
		ModelAndView mv = new ModelAndView("admin/content/channel_list"); 
		List<Channel> channelList=channelService.list();
		mv.addObject("channels", channelList);
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView delete(String channel_id){
		ModelAndView mv = new ModelAndView("redirect:/admin/channel/list"); 
		channelService.deleteById(Integer.parseInt(channel_id));
		return mv;
	}
}
