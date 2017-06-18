package com.dgjs.controller.admin.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.Constants;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Comments;
import com.dgjs.service.content.CommentsService;
import com.dgjs.utils.StringUtils;

@Controller
@RequestMapping("/admin")
public class CommentsController {

	@Autowired
	CommentsService commentsService;
	
	@RequestMapping("/comments")
	public ModelAndView comments(String articlescrapId,String currentPage){
		ModelAndView mv = new ModelAndView("admin/content/comments");
		PageInfoDto<Comments> pageInfoDto=commentsService.getCommentsByArticlescrapId(articlescrapId, StringUtils.parseInt(currentPage,1), Constants.DEFAULT_ONEPAGESIZE, true);
		mv.addObject("pageInfo",pageInfoDto);
		return mv;
	}
	
	@RequestMapping("/updateComments")
	public ModelAndView updateComments(String id,Boolean isShow,String desc,String articlescrapId){
	   ModelAndView mv = new ModelAndView("redirect:/admin/comments?articlescrapId="+articlescrapId);  
	   commentsService.updateStatus(id, isShow, desc);
	   return mv;
	}
}
