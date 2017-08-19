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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.dgjs.constants.Constants;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.dto.timeline.Asset;
import com.dgjs.model.dto.timeline.Dat;
import com.dgjs.model.dto.timeline.Timeline;
import com.dgjs.model.dto.timeline.TimelineView;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.param.view.TimeLineView;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.utils.StringUtils;

@Controller
public class TimeLineController {
	
	@Autowired
	ArticlescrapService articlescrapService;
	
	@Autowired
	PictureService pictureService;
	
	@RequestMapping("/timeline")
    public ModelAndView index(TimeLineView view) throws Exception {  
		ModelAndView mv = new ModelAndView("front/common/timeline2");
		//加载分类
		mv.addObject("types", Articlescrap_Type.values());
		mv.addObject("isContain", view.getIsContain());
		mv.addObject("isNext", String.valueOf(view.getIsNext()==null?true:view.getIsNext()));
		mv.addObject("isSlip", String.valueOf(view.getIsSlip()==null?false:view.getIsSlip()));
		mv.addObject("timeline",view);
		//打点数据
		mv.addObject("pageid", Constants.DD_TIMELINE_ID);
		return mv;
    }
	
	@ResponseBody
	@RequestMapping(value = "/getstroies.json")
	public void getstroies(TimeLineView view,HttpServletResponse response,HttpServletRequest request){
		TimelineView tv = null;
		int onePageSize=8;
		String contextPath = (String) request.getAttribute("contextPath");
		Articlescrap articlescrap = null;
		Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
		sort.put("start_time", SortOrder.ASC);
		boolean isNext = view.getIsNext() == null ? true : view.getIsNext();
		if(StringUtils.isNullOrEmpty(view.getArticlescrapId())){
			ArticlescrapCondtion contion = new ArticlescrapCondtion();
	    	contion.setCurrentPage(1);
	    	contion.setOnePageSize(1);
	    	contion.setStartTimeFrom(-10000);
	    	contion.setSort(sort);
	    	contion.setStatus(UpDown_Status.UP);
	    	PageInfoDto<Articlescrap> page=articlescrapService.listArticlescrap(contion);
	    	if(page!=null && page.getObjects()!=null && page.getObjects().size()>0){
	    	    articlescrap = page.getObjects().get(0);
	    	}
		}else{
			String[] aid={view.getArticlescrapId()};
			List<Articlescrap> list=articlescrapService.getArticlescrapByIds(aid);
			if(list != null && list.size() != 0){
				articlescrap = list.get(0);
			}
		}
		if(articlescrap!=null && articlescrap.getBegin_time()!=null){
		    tv = new TimelineView();
			ArticlescrapCondtion contion = new ArticlescrapCondtion();
			List<Articlescrap> aList = null;
			if(view.getIsSlip()){
				contion.setCurrentPage(1);
		    	contion.setOnePageSize(onePageSize);
		    	contion.setType(view.getType());
		    	contion.setKeyword(view.getKeyword());
		    	contion.setStatus(UpDown_Status.UP);
		    	if(isNext){
		    		contion.setSort(sort);
		    		if(view.getIsContain()==1){
		    			contion.setGreaterStartTime(articlescrap.getBegin_time());
			    	}else{
			    		contion.setStartTimeFrom(articlescrap.getBegin_time());
			    	}
		    	}else{
		    		sort.put("start_time", SortOrder.DESC);
		    		contion.setSort(sort);
		    		if(view.getIsContain()==1){
		    			contion.setLessThanStartTime(articlescrap.getBegin_time());
			    	}else{
			    		contion.setStartTimeTo(articlescrap.getBegin_time());
			    	}
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
				contion.setType(view.getType());
		    	contion.setKeyword(view.getKeyword());
		    	contion.setStatus(UpDown_Status.UP);
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
				contion.setType(view.getType());
		    	contion.setKeyword(view.getKeyword());
		    	contion.setStatus(UpDown_Status.UP);
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
	    	    	if(view.getIsSlip()){
	    	    		articlescrap = list.get(0);
		    	    }
	    	    }else{
	    	    	tv.setMaxTimeAid(list.get(0).getId());
	    	    	tv.setMinTimeAid(list.get(list.size()-1).getId());
	    	    	if(view.getIsSlip()){
	    	    		articlescrap = list.get(list.size()-1);
		    	    }
	    	    }
	    	    //组装数据
	    	    Timeline timeline = getTimeLine(list,contextPath,pictureService.getImageContextPath(),articlescrap,view.getType());
	    		tv.setTimeline(timeline);
	    	}
		}
		PrintWriter pw = null;
		try {
			processNullValue(tv,isNext,articlescrap==null?null:articlescrap.getId(),contextPath,view.getType());
			response.setCharacterEncoding("utf-8");
    		response.setContentType("application/json; charset=utf-8"); 
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
	
	private Timeline getTimeLine(List<Articlescrap> list,String contextPath,String imageContextPath,Articlescrap articlescrap,Articlescrap_Type type){
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
		ast.setCaption(StringUtils.jointString("简史",type==null?"":type.getValue(),"时间轴"));
		ast.setCredit("19世纪的百年资料");
		ast.setMedia("http://img.taopic.com/uploads/allimg/140326/235113-1403260U22059.jpg");
		ast.setStart("-1000");
		Timeline timeline = new Timeline();
		timeline.setAsset(ast);
		timeline.setDate(dts);
		timeline.setHeadline(ast.getCaption());
		timeline.setStartDate(articlescrap.getYear()+"");
		timeline.setText("人文与情怀的一次共舞");
		return timeline;
	}
	
	private void processNullValue(TimelineView tv,boolean isNext,String aid,String contextPath,Articlescrap_Type type){
		boolean isAllParamNull=tv.getTimeline()==null&&StringUtils.isNullOrEmpty(tv.getMaxTimeAid())&&StringUtils.isNullOrEmpty(tv.getMinTimeAid());
		if(tv == null||isAllParamNull){
			List<Dat> dts = new ArrayList<>();
			Asset one = new Asset();
			Dat dt = new Dat();
			dt.setAsset(one);
			dt.setHeadline("没有更多的文章了");
			dt.setText("");
			one.setMedia("http://www.cwillow.com/images/editor/p_100/20170729111418454997.jpg");
			dt.setIsfirst("1");
			dts.add(dt);
			Asset ast = new Asset();
			ast.setCaption(StringUtils.jointString("简史",type==null?"":type.getValue(),"时间轴"));
			ast.setCredit("19世纪的百年资料");
			ast.setMedia("http://img.taopic.com/uploads/allimg/140326/235113-1403260U22059.jpg");
			ast.setStart("-1000");
			Timeline timeline = new Timeline();
			timeline.setAsset(ast);
			timeline.setDate(dts);
			timeline.setHeadline(ast.getCaption());
			timeline.setText("人文与情怀的一次共舞");
			//如果是上翻到顶了
			if(!isNext){
				dt.setStartDate("-3000s");
				timeline.setStartDate("-3000");
				tv.setPosition(1);
			}else{
				dt.setStartDate("3000s");
				timeline.setStartDate("3000");
				tv.setPosition(2);
			}
			tv.setMaxTimeAid(aid);
			tv.setMinTimeAid(aid);
			tv.setIsHaveValue(0);
			tv.setTimeline(timeline);
		}
	}
}
