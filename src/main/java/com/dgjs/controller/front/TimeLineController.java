package com.dgjs.controller.front;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response,Articlescrap_Type type,String keyword) throws Exception {  
		ModelAndView mv = new ModelAndView("front/timeline2");
		//加载分类
		mv.addObject("types", Articlescrap_Type.values());
		return mv;
    }
	
	@ResponseBody
	@RequestMapping(value = "/getstroies.json")
	public void getstroies(String articlescrapId,Integer currentPage,HttpServletResponse response,HttpServletRequest request){
		int gap=50;
		if(currentPage==null){
			currentPage=1;
		}
		if(StringUtils.isEmpty(articlescrapId)){
			articlescrapId = "AVzZrY1I9b4MAjksb_WD";
		}
		
		String[] aid={articlescrapId};
		List<Articlescrap> list=articlescrapService.getArticlescrapByIds(aid);
		Articlescrap articlescrap=list.get(0);
	    Integer year=articlescrap.getYear();
	    List<Dat> dts = new ArrayList<>();
	    String contextPath=(String) request.getAttribute("contextPath");
	    String imageContextPath=pictureService.getImageContextPath();
	    if(year!=null){
	    	ArticlescrapCondtion contion = new ArticlescrapCondtion();
	    	contion.setCurrentPage(currentPage);
	    	contion.setOnePageSize(20);
	    	PageInfoDto<Articlescrap> page=articlescrapService.listArticlescrap(contion);
	    	List<Articlescrap> aList=page.getObjects();
	    	for(Articlescrap a:aList){
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
				if(a.getId().equals(articlescrapId)){
					dt.setIsfirst("1");
				}
				dts.add(dt);
	    	}
	    }
	    
		
		
		Asset ast = new Asset();
		ast.setCaption("大国简史正史时间轴");
		ast.setCredit("19世纪的百年资料");
		ast.setMedia("http://img.taopic.com/uploads/allimg/140326/235113-1403260U22059.jpg");
		
		
		
//		List<Dat> dts = new ArrayList<>();
//		Random rndm = new Random();
//		for(int i = 0 ; i< 10 ; i++){
//			Asset one = new Asset();
//			one.setCaption("<a href='/show/AV0DmzcMqMQTX7aOp80m'>阅读原文 >></a>");
//			one.setCredit("资料编号" + rndm.nextLong());
//			one.setMedia("http://img.taopic.com/uploads/allimg/140326/235113-1403260U22059.jpg");
//			if(i == 5){
//				Dat dt = new Dat();
//				dt.setAsset(one);
//				dt.setHeadline("特殊设置的时轴起始页码");
//				dt.setStartDate("201"+i);
//				dt.setIsfirst("1");
//				dt.setText("【大国简史】第"+i+"次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想");
//				dts.add(dt);
//				continue;
//			}
//			Dat dt = new Dat();
//			dt.setAsset(one);
//			dt.setHeadline("各国领导人年轻时的照片");
//			dt.setStartDate("201"+i);
//			dt.setText("【大国简史】第"+i+"次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想");
//			dts.add(dt);
//		}
//		
//		for(int i = 0 ; i< 10 ; i++){
//			Asset one = new Asset();
//			one.setCaption("<a href='/show/AV0DmzcMqMQTX7aOp80m'>阅读原文 >></a>");
//			one.setCredit("通信凭证" + rndm.nextLong());
//			one.setMedia("");
//			
//			Dat dt = new Dat();
//			dt.setAsset(one);
//			dt.setHeadline("各国领导人年轻时的照片");
//			dt.setStartDate("201"+i);
//			dt.setText("【大国简史】第"+i+"次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想次text测试想");
//			dts.add(dt);
//		}
		
		Timeline timeline = new Timeline();
		timeline.setAsset(ast);
		timeline.setDate(dts);
		timeline.setHeadline("大国简史正史时间轴");
		timeline.setStartDate(articlescrap.getYear()+"");
		timeline.setText("人文与情怀的一次共舞");
		
		TimelineView tv = new TimelineView();
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
