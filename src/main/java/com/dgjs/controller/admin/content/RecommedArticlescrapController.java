package com.dgjs.controller.admin.content;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.view.BaseView;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.RecommedArticlescrapService;

@Controller
@RequestMapping("/admin")
public class RecommedArticlescrapController {
	
	private Log log = LogFactory.getLog(RecommedArticlescrapController.class);

	@Autowired
	RecommedArticlescrapService recommedArticlescrapService;
	
	@Autowired
	ArticlescrapService articlescrapSerivce;
	
	
	@RequestMapping("/recommedArticlescrapList")
	public ModelAndView recommedArticlescrapList(){
		ModelAndView mv = new ModelAndView("admin/content/recommedArticlescrap_list");  
		List<Articlescrap> list=recommedArticlescrapService.list(null);
		mv.addObject("recommedArticlescrapList", list);
		return mv;
	}
	
	@RequestMapping("/recommedArticlescrap")
	public ModelAndView recommedArticlescrap(){
		ModelAndView mv = new ModelAndView("admin/content/recommedArticlescrap");  
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/ajaxSaveRecommedArticlescrap")
	public BaseView ajaxSaveRecommedArticlescrap(String id,Integer sort,UpDown_Status status){
		BaseView view=new BaseView();
		if(StringUtils.isEmpty(id)||sort==null||status ==null||sort<0){
			view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return view;
		}
		try{
			Articlescrap articlescrap=articlescrapSerivce.selectById(id);
			if(articlescrap==null){
				view.setBaseViewValue("NO_ARTICLESCRAP", "没有对应的文章");
				return view;
			}
			if(articlescrap.getRecommend().getSort() > -1){
				 view.setBaseViewValue("DUPLICATE_ARTICLESCRAP", "此文章已经是推荐文章");
			}else{
				int flag=recommedArticlescrapService.save(id, status, sort);
				if(flag!=1)
				  view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			}
		}catch(Exception e){
			log.error("SERVICE_ERROR", e);
			view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return view;
	}
	
	@RequestMapping("/deleteRecommedArticlescrap")
	public ModelAndView deleteRecommedArticlescrap(String recommedArticlescrapId) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/recommedArticlescrapList"); 
		recommedArticlescrapService.deleteById(recommedArticlescrapId);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/ajaxUpdateRAStatus")
	public BaseView ajaxUpdateStatus(String recommedArticlescrapId,UpDown_Status status){
		BaseView view=new BaseView();
		if(recommedArticlescrapId==null||status==null){
			view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return view;
		}
		try{
			int number=recommedArticlescrapService.updateStatus(recommedArticlescrapId,status);
			if(number!=1){
				view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.getValue(),"无效的推荐id");
			}
		}catch(Exception e){
			log.error("SERVICE_ERROR", e);
			view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return view;
	}
}
