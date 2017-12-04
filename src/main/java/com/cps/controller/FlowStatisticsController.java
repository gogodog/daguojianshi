package com.cps.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.common.DataService;
import com.dgjs.service.content.ArticlescrapService;

@Controller
@RequestMapping("/cps/flwststcs")
public class FlowStatisticsController {

	@Autowired
	ArticlescrapService articlescrapSerivce;
	
	@Autowired
	DataService dataSerivce;
	
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("/cps/flwststcs");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/getArticlescrapsVisits")
	public Object getArticlescrapsVisits(Integer currentPage){
		JSONObject json = new JSONObject();
		ArticlescrapCondtion articlescrapCondtion = new ArticlescrapCondtion();
		if(currentPage!=null){
			articlescrapCondtion.setCurrentPage(currentPage);
		}
		Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
		sort.put("show_time", SortOrder.DESC);
		articlescrapCondtion.setSort(sort);
//		articlescrapCondtion.setUserId(WebContextHelper.getUserId());
		String[] includes = {"id","title"};
		articlescrapCondtion.setIncludes(includes);
		PageInfoDto<Articlescrap> pageinfo = articlescrapSerivce.listArticlescrap(articlescrapCondtion);
		List<Articlescrap> list = pageinfo.getObjects();
		List<String> ids = new ArrayList<String>();
		if(list!=null && list.size()>0){
			for(Articlescrap articlescrap:list){
				ids.add(articlescrap.getId());
			}
			Map<String,Long> map=dataSerivce.getDocShowCounts(ids);
			List<String> titles = new ArrayList<String> ();
			List<Long> visits = new ArrayList<Long> ();
			for(Articlescrap articlescrap:list){
				titles.add(articlescrap.getTitle());
				visits.add(map.get(articlescrap.getId()));
			}
			json.put("titles", titles);
			json.put("visits", visits);
			json.put("ids", ids);
			json.put("haveNext", pageinfo.getCurrentPage() < pageinfo.getTotalPage());
			json.put("havePrev", pageinfo.getCurrentPage() > 1 ? true : false);
			json.put("currentPage", pageinfo.getCurrentPage());
		}
		return json;
	}
}
