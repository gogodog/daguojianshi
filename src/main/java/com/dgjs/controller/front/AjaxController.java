package com.dgjs.controller.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.common.DataService;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.ArticlescrapService;

@Controller
public class AjaxController {

	@Autowired
	ArticlescrapService articlescrapService;
	@Autowired
	PictureService pictureService;
	@Autowired
	DataService dataSerivce;
	
	@RequestMapping("/alist")
	@ResponseBody
    public Object list(HttpServletRequest request, HttpServletResponse response,ArticlescrapCondtion condition) throws Exception {  
		JSONObject list = new JSONObject();
		Map<String,SortOrder> sort = new HashMap<String,SortOrder>();
		sort.put("show_time", SortOrder.DESC);
		condition.setSort(sort);
		PageInfoDto<Articlescrap> pageInfo=articlescrapService.listArticlescrap(condition);
		list.put("pageInfo", pageInfo);
		list.put("imageContextPath", pictureService.getImageContextPath());
		//加载文章阅读量
		List<Articlescrap> aticlescrapList = pageInfo == null?null:pageInfo.getObjects();
		if(aticlescrapList!=null&&aticlescrapList.size()>0){
			List<String> articlescrapIds = new ArrayList<String>();
			for(Articlescrap articlescrap:aticlescrapList){
				articlescrapIds.add(String.valueOf(articlescrap.getId()));
			}
			Map<String,Integer> map=dataSerivce.getDocShowCounts(articlescrapIds);
			list.put("visits", map);
		}
		list.put("isTypeShow", condition.getType()==null?true:false);
		return list;
    } 
}
