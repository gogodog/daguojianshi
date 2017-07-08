package com.dgjs.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/feedback")
public class FeedBackController {
	
	@RequestMapping(value = "/{docid}")
	public ModelAndView ajaxJudge(Model model, @PathVariable("docid")String docid){
		ModelAndView mv = new ModelAndView("front/feedback");
		mv.addObject("docid", docid);
		return mv;
	}
}
