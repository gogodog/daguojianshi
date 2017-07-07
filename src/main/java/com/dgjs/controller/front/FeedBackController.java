package com.dgjs.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FeedBackController {
	
	@RequestMapping(value = "/feedback/{docid}/fees.html")
	public String ajaxJudge(Model model, @PathVariable("docid")String docid){
		model.addAttribute("docid", docid);
		return "feedback/feedback";
	}
}
