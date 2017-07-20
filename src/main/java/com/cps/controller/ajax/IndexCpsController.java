package com.cps.controller.ajax;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cps.model.DocView;
import com.cps.model.DraftView;
import com.dgjs.model.common.PageBean;

import freemarker.log.Logger;

/**
 * 内容生产系统首页
 * @author jiadong.wen
 *
 */
@Controller
@RequestMapping("/cps")
public class IndexCpsController {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping("/index")
    public String index(HttpServletRequest request) throws Exception {  
		return "/cps/index";
    }
	
	@RequestMapping("/docms")
    public String docms(Model model, PageBean pageBean) throws Exception {
		
		//TODO
		List<DocView> list = new ArrayList<DocView>();
		for(int i = 0 ; i<10 ; i++){
			DocView es = new DocView();
			es.setId("dgjs"+i);
			es.setName("IS在中东大势已去 库尔德人或成新“以色列”");
			es.setSh(new Date());
			es.setStatus("提审中");
			es.setTs(new Date());
			es.setSrctype(i%6==0?"原创":"转载");
			es.setCategory(i%7==1?"正史":"地理");
			list.add(es);
		}
		
		pageBean.setRowsCount(21);
		
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("list", list);
		return "/cps/docms";
    }
	
	@RequestMapping("/wdoc")
    public String wdoc(HttpServletRequest request) throws Exception {  
		return "/cps/wdoc";
    }
	
	@RequestMapping("/draft")
    public String draft(Model model, PageBean pageBean) throws Exception {
		//TODO
		List<DraftView> list = new ArrayList<DraftView>();
		for(int i = 0 ; i<10 ; i++){
			DraftView es = new DraftView();
			es.setId("dgjs"+i);
			es.setName("IS在中东大势已去 库尔德人或成新“以色列”");
			es.setLast(new Date());
			list.add(es);
		}
		pageBean.setRowsCount(21);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("list", list);
		return "/cps/draft";
    }
	
	@RequestMapping("/chart")
    public String chart(HttpServletRequest request) throws Exception {  
		return "/cps/index";
    }
	
	@RequestMapping("/source")
    public String source(HttpServletRequest request) throws Exception {  
		return "/cps/source";
    }
	
	@RequestMapping("/psoninf")
    public String psoninf(HttpServletRequest request) throws Exception {  
		return "/cps/psoninf";
    }
	
	@RequestMapping("/feedback")
    public String feedback(HttpServletRequest request) throws Exception {  
		return "/cps/feedback";
    }
}
