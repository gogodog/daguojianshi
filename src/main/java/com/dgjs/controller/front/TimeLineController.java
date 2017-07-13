package com.dgjs.controller.front;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
		ast.setCaption("大国简史");
		ast.setCredit("1900-2001");
		ast.setMedia("http://www.cwillow.com/images/editor/p1/20170614040110908618.jpg");
		
		List<Dat> dts = new ArrayList<>();
		
		for(int i = 0 ; i< 10 ; i++){
			Dat dt = new Dat();
			Asset one = new Asset();
			ast.setCaption("大清复明1");
			ast.setCredit("大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明");
			ast.setMedia("http://www.cwillow.com/images/editor/p1/20170614040110908618.jpg");
			dt.setAsset(one);
			dt.setHeadline("'The XX4mmys - "+i+"fdsafdsafdsafdsafdsafdsafdsa'");
			dt.setStartDate("201"+i);
			dt.setText("【大国简史】第"+i+"次text测试");
			dts.add(dt);
		}
		
		for(int i = 0 ; i< 10 ; i++){
			Dat dt = new Dat();
			Asset one = new Asset();
			ast.setCaption("大清复明2");
			ast.setCredit("大清复明大清复明大清复明大清复明大清复明");
			ast.setMedia("http://www.cwillow.com/images/editor/p1/20170614040110908618.jpg");
			dt.setAsset(one);
			dt.setHeadline("'The XX3mmys - "+i+"fdsafdsafdsafdsafdsafdsafdsa'");
			dt.setStartDate("201"+i);
			dt.setText("【大国简史】第"+i+"次text测试");
			dts.add(dt);
		}
		
		for(int i = 0 ; i< 10 ; i++){
			Dat dt = new Dat();
			Asset one = new Asset();
			ast.setCaption("大清复明3");
			ast.setCredit("大清复明大清复明大清复明大清复明大清复明");
			ast.setMedia("http://www.cwillow.com/images/editor/p1/20170614040110908618.jpg");
			dt.setAsset(one);
			dt.setHeadline("'The XX1mmys - "+i+"fdsafdsafdsafdsafdsafdsafdsa'");
			dt.setStartDate("201"+i);
			dt.setText("【大国简史】第"+i+"次text测试");
			dts.add(dt);
		}
		
		for(int i = 0 ; i< 10 ; i++){
			Dat dt = new Dat();
			Asset one = new Asset();
			ast.setCaption("大清复明4");
			ast.setCredit("大清复明大清复明大清复明大清复明大清复明");
			ast.setMedia("http://www.cwillow.com/images/editor/p1/20170614040110908618.jpg");
			dt.setAsset(one);
			dt.setHeadline("'The XX2mmys - "+i+"fdsafdsafdsafdsafdsafdsafdsa'");
			dt.setStartDate("201"+i);
			dt.setText("【大国简史】第"+i+"次text测试");
			dts.add(dt);
		}
		
		for(int i = 0 ; i< 10 ; i++){
			Dat dt = new Dat();
			Asset one = new Asset();
			ast.setCaption("大清复明");
			ast.setCredit("大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明");
			ast.setMedia("http://www.cwillow.com/images/editor/p1/20170614040110908618.jpg");
			dt.setAsset(one);
			dt.setHeadline("'The TTammys - "+i+"fdsafdsafdsafdsa'");
			dt.setStartDate("203"+i);
			dt.setText("【大国简史】第"+i+"次text测试大清复明大清复明大清复明大清复明大清复明大清复明大清复明大明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大<a>明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清</a>复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明大清复明");
			dts.add(dt);
		}
		
		for(int i = 0 ; i< 10 ; i++){
			Dat dt = new Dat();
			Asset one = new Asset();
			ast.setCaption("大清复明");
			ast.setCredit("大清复明大清复明大清复明大清复明大清复明");
			ast.setMedia("http://www.youtubi.com/images/editor/p1/20170614040110908618.jpg");
			dt.setAsset(one);
			dt.setHeadline("'The YYmmys - "+i+"'");
			dt.setStartDate("204"+i);
			dt.setText("【大国简史】第"+i+"次text测试");
			dts.add(dt);
		}
		
		Timeline timeline = new Timeline();
		timeline.setAsset(ast);
		timeline.setDate(dts);
		timeline.setHeadline("http://www.json.cn/");
		timeline.setStartDate("2005");
		timeline.setText("ceshi");
		
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
