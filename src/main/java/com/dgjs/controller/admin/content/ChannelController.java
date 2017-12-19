package com.dgjs.controller.admin.content;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.controller.front.FeedBackController;
import com.dgjs.model.dto.ChannelArticlescrapDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.persistence.Channel;
import com.dgjs.model.persistence.ChannelArticlescrap;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.ChannelService;
import com.dgjs.utils.StringUtils;

@Controller
@RequestMapping("/admin/channel")
public class ChannelController {

	@Autowired
	ChannelService channelService;
	
	@Autowired
	ArticlescrapService articlescrapSerivce;
	
	private Log log = LogFactory.getLog(FeedBackController.class);
	
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@LogRecord(operate=OperateEnum.Add,remark="保存频道")
	public BaseView saveChannel(Channel channel){
		BaseView mv = new BaseView(); 
		if(channel.getC_name()==null||channel.getC_name().length()>10){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}else if(channel.getStatus()==null){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		try{
			int f=0;
			if(channel.getId()==null){
			   f=channelService.save(channel);
			}else{
			   f=channelService.update(channel);
			}
			if(f<1){
				mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			}
		}catch(Exception e){
			log.error("saveChannel exception", e);
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
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
	@LogRecord(operate=OperateEnum.Delete,remark="删除频道")
	public ModelAndView delete(String channel_id){
		ModelAndView mv = new ModelAndView("redirect:/admin/channel/list"); 
		channelService.deleteById(Integer.parseInt(channel_id));
		return mv;
	}
	
	@RequestMapping("/caList")
	public ModelAndView caList(Integer channelId,String channelName){
		ModelAndView mv = new ModelAndView("/admin/content/ca_list"); 
		List<ChannelArticlescrapDto> list=channelService.listCA(channelId);
		mv.addObject("caList", list);
		mv.addObject("channelName", channelName);
		mv.addObject("channelId", channelId);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteCA",method=RequestMethod.POST)
	@LogRecord(operate=OperateEnum.Delete,remark="删除频道文章")
	public BaseView deleteCA(String caId){
		BaseView mv = new BaseView(); 
	    if(StringUtils.isNullOrEmpty(caId)){
	    	mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
	    }
	    try{
	    	int f=channelService.deleteCA(caId);
	    	if(f<1){
	    		mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
				return mv;
	    	}
	    }catch(Exception e){
	    	log.error("deleteCA exception", e);
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
	    }
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/saveCA")
	@LogRecord(operate=OperateEnum.Add,remark="保存频道文章")
	public BaseView saveCA(ChannelArticlescrap ca){
		BaseView mv = new BaseView();
		if(ca.getChannel_id()==null||StringUtils.isNullOrEmpty(ca.getArticlescrap_id())){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		try{
			Articlescrap articlescrap=articlescrapSerivce.selectByIdAll(ca.getArticlescrap_id());
			if(articlescrap==null){
				mv.setBaseViewValue(RETURN_STATUS.SERVICE_ERROR.toString(),"错误的文章id");
				return mv;
			}
			int f=channelService.saveCA(ca);
			if(f<1){
				mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
				return mv;
			}
		}catch(Exception e){
			log.error("saveCA exception", e);
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateCA",method=RequestMethod.POST)
	@LogRecord(operate=OperateEnum.Update,remark="修改频道文章")
	public BaseView updateCA(String caId,Integer sort){
		BaseView mv = new BaseView();
		if(StringUtils.isNullOrEmpty(caId)||sort==null){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		try{
			int f=channelService.updateCA(caId, sort);
			if(f<1){
				mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
				return mv;
			}
		}catch(Exception e){
			log.error("updateCA exception", e);
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			return mv;
		}
		return mv;
	}
}
