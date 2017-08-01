package com.dgjs.controller.front;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.dto.timeline.Asset;
import com.dgjs.model.dto.timeline.Dat;
import com.dgjs.model.dto.timeline.Timeline;
import com.dgjs.model.dto.timeline.TimelineView;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.ArticlescrapService;

@Controller
public class TimeLineController {
	
	@Autowired
	ArticlescrapService articlescrapService;
	
	@Autowired
	PictureService pictureService;
	
	@RequestMapping("/timeline")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response,Articlescrap_Type type,
    		String keyword,String articlescrapId,Boolean isNext,Boolean isSlip) throws Exception {  
		ModelAndView mv = new ModelAndView("front/timeline2");
		//加载分类
		mv.addObject("types", Articlescrap_Type.values());
		mv.addObject("articlescrapId", articlescrapId);
		mv.addObject("isNext", String.valueOf(isNext==null?true:isNext));
		mv.addObject("isSlip", String.valueOf(isSlip==null?false:isSlip));
		return mv;
    }
	
	@ResponseBody
	@RequestMapping(value = "/getstroies.json")
	public void getstroies(String articlescrapId,Boolean isNext,Boolean isSlip,HttpServletResponse response,HttpServletRequest request){
		int onePageSize=5;
		String contextPath = (String) request.getAttribute("contextPath");
		Articlescrap articlescrap = null;
		Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
		sort.put("start_time", SortOrder.ASC);
		isNext = isNext == null ? true : isNext;
		if(StringUtils.isEmpty(articlescrapId)){
			ArticlescrapCondtion contion = new ArticlescrapCondtion();
	    	contion.setCurrentPage(1);
	    	contion.setOnePageSize(1);
	    	contion.setStartTimeFrom(-10000);
	    	contion.setSort(sort);
	    	PageInfoDto<Articlescrap> page=articlescrapService.listArticlescrap(contion);
	    	if(page!=null && page.getObjects()!=null && page.getObjects().size()>0){
	    	    articlescrap = page.getObjects().get(0);
	    	}
		}else{
			String[] aid={articlescrapId};
			List<Articlescrap> list=articlescrapService.getArticlescrapByIds(aid);
			if(list != null && list.size() != 0){
				articlescrap = list.get(0);
			}
		}
		if(articlescrap!=null && articlescrap.getBegin_time()!=null){
			TimelineView tv = new TimelineView();
			ArticlescrapCondtion contion = new ArticlescrapCondtion();
			List<Articlescrap> aList = null;
			if(isSlip){
				contion.setCurrentPage(1);
		    	contion.setOnePageSize(onePageSize);
		    	if(isNext){
		    		contion.setSort(sort);
		    		contion.setGreaterStartTime(articlescrap.getBegin_time());
		    	}else{
		    		sort.put("start_time", SortOrder.DESC);
		    		contion.setSort(sort);
		    		contion.setLessThanStartTime(articlescrap.getBegin_time());
		    	}
		    	PageInfoDto<Articlescrap> page= articlescrapService.listArticlescrap(contion);
		    	if(page!=null && page.getObjects()!=null && page.getObjects().size()>0){
		    		 aList = page.getObjects();
		    	}
			}else{
				int preNum = onePageSize/2;
				int nextNum = onePageSize - preNum;
				List<Articlescrap> prevList =null;
				List<Articlescrap> nextList =null;
				sort.put("start_time", SortOrder.DESC);
				contion.setSort(sort);
				contion.setOnePageSize(preNum);
				contion.setLessThanStartTime(articlescrap.getBegin_time());
				PageInfoDto<Articlescrap> page= articlescrapService.listArticlescrap(contion);
				if(page!=null && page.getObjects()!=null && page.getObjects().size()>0){
					prevList = page.getObjects();
				}
				if(prevList==null || prevList.size()==0){
					nextNum = onePageSize;
				}else if(prevList.size() < preNum){
					nextNum = onePageSize - prevList.size();
				}
				contion = new ArticlescrapCondtion();
				contion.setOnePageSize(nextNum);
				contion.setStartTimeFrom(articlescrap.getBegin_time());
				sort.put("start_time", SortOrder.ASC);
				contion.setSort(sort);
				page= articlescrapService.listArticlescrap(contion);
				if(page!=null && page.getObjects()!=null && page.getObjects().size()>0){
					nextList = page.getObjects();
				}
				if(prevList!=null || nextList!=null){
					aList = new ArrayList<Articlescrap>();
					if(prevList!=null){
						aList.addAll(prevList);
					}
					if(nextList!=null){
						aList.addAll(nextList);
					}
				}
			}
	    	
	    	if(aList!=null && aList.size()>0){
	    	    List<Articlescrap> list= aList;
	    	    //如果是升序排列
	    	    if(sort.get("start_time").equals(SortOrder.ASC)){
	    	    	tv.setMaxTimeAid(list.get(list.size()-1).getId());
	    	    	tv.setMinTimeAid(list.get(0).getId());
	    	    	if(isSlip){
	    	    		articlescrap = list.get(0);
		    	    }
	    	    }else{
	    	    	tv.setMaxTimeAid(list.get(0).getId());
	    	    	tv.setMinTimeAid(list.get(list.size()-1).getId());
	    	    	if(isSlip){
	    	    		articlescrap = list.get(list.size()-1);
		    	    }
	    	    }
	    	    //组装数据
	    	    Timeline timeline = getTimeLine(list,contextPath,pictureService.getImageContextPath(),articlescrap);
	    		tv.setTimeline(timeline);
	    		response.setCharacterEncoding("utf-8");
	    		response.setContentType("application/json; charset=utf-8"); 
	    		PrintWriter pw = null;
	    		try {
	    			pw = response.getWriter();
	    			pw.write(JSONObject.toJSONString(tv));
	    			pw.flush();
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} finally{
	    			if(pw != null){
	    				pw.flush();
	    				pw.close();
	    			}
	    		}
	    	}
		}
	}
	
	private Timeline getTimeLine(List<Articlescrap> list,String contextPath,String imageContextPath,Articlescrap articlescrap){
		List<Dat> dts = new ArrayList<>();
    	for(Articlescrap a:list){
    		Asset one = new Asset();
			one.setCaption("<a href='"+contextPath+"/show/"+a.getId()+"'>阅读原文 >></a>");
			one.setCredit("资料编号" + a.getStart_time());
			Dat dt = new Dat();
			dt.setAsset(one);
			dt.setHeadline(a.getTitle());
			dt.setStartDate(a.getYear()+"");
			if(a.getSub_content().length()>100){
				dt.setText("");
				one.setMedia(imageContextPath+a.getShow_picture());
			}else{
				dt.setText(a.getSub_content()+"<br><a href='"+contextPath+"/show/"+a.getId()+"'>阅读原文 >></a>");
			}
			if(a.getId().equals(articlescrap.getId())){
				dt.setIsfirst("1");
			}
			dts.add(dt);
    	}
    	Asset ast = new Asset();
		ast.setCaption("大国简史正史时间轴");
		ast.setCredit("19世纪的百年资料");
		ast.setMedia("http://img.taopic.com/uploads/allimg/140326/235113-1403260U22059.jpg");
		ast.setStart("-1000");
		Timeline timeline = new Timeline();
		timeline.setAsset(ast);
		timeline.setDate(dts);
		timeline.setHeadline("大国简史正史时间轴");
		timeline.setStartDate(articlescrap.getYear()+"");
		timeline.setText("人文与情怀的一次共舞");
		return timeline;
	}
}
