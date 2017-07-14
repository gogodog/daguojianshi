package com.dgjs.controller.front;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.dgjs.model.dto.timeline.Asset;
import com.dgjs.model.dto.timeline.Dat;
import com.dgjs.model.dto.timeline.Timeline;
import com.dgjs.model.dto.timeline.TimelineView;
import com.dgjs.model.enums.Articlescrap_Type;

@Controller
public class TimeLineController {
	
	@RequestMapping("/timeline")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response,Articlescrap_Type type,String keyword) throws Exception {  
		ModelAndView mv = new ModelAndView("front/timeline2");
		//加载分类
		mv.addObject("types", Articlescrap_Type.values());
		return mv;
    }
	
	@ResponseBody
	@RequestMapping(value = "/getstroies.json")
	public void getstroies(Integer currentPage,HttpServletResponse response){
		Asset ast = new Asset();
		ast.setCaption("大国简史正史时间轴");
		ast.setCredit("19世纪的百年资料");
		ast.setMedia("http://www.cwillow.com/images/editor/p1/20170614040110908618.jpg");
		
		List<Dat> dts = new ArrayList<>();
		Random rndm = new Random();
		for(int i = 0 ; i< 10 ; i++){
			Asset one = new Asset();
			one.setCaption(i + "：测试数据");
			one.setCredit("通信凭证" + rndm.nextLong());
			one.setMedia("http://www.cwillow.com/images/editor/p1/20170614040110908618.jpg");
			
			Dat dt = new Dat();
			dt.setAsset(one);
			dt.setHeadline("各国领导人年轻时的照片");
			dt.setStartDate("201"+i);
			dt.setText("【大国简史】第"+i+"次text测试");
			dts.add(dt);
		}
		
		Timeline timeline = new Timeline();
		timeline.setAsset(ast);
		timeline.setDate(dts);
		timeline.setHeadline("大国简史正史时间轴");
		timeline.setStartDate("2005");
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
