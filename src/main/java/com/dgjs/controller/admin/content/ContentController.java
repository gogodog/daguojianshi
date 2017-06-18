package com.dgjs.controller.admin.content;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dgjs.mapper.content.ArticlescrapMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.es.ArticlescrapEs;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.utils.DateUtils;

@Controller
@RequestMapping("/admin")
public class ContentController {

	@Autowired
	ArticlescrapMapper articlescrapMapper;
	
	@Autowired
	ArticlescrapService articlescrapSerivce;
	
	@RequestMapping("/contentList")
    public ModelAndView helloWord(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("admin/content/index");  
        mv.addObject("title", "Spring MVC And Freemarker");  
        mv.addObject("content", " Hello world ， test my first spring mvc ! ");  
        
//        ArticlescrapCondtion condtion = new ArticlescrapCondtion();
//        condtion.setOnePageSize(30);
//        List<Articlescrap> list=articlescrapMapper.listArticlescrap(condtion);
//        for(Articlescrap articlescrap:list){
//        	articlescrap=articlescrapMapper.selectById(Long.parseLong(articlescrap.getId()));
//        	articlescrapSerivce.saveArticlescrap(articlescrap);
//        }
        
//        Date now=new Date();
//        Articlescrap articlescrap = new Articlescrap();
//        articlescrap.setAuthor("镜花水月");
//        articlescrap.setContent("test test test test content 2");
//        articlescrap.setCreate_time(now);
//        articlescrap.setShow_picture("http://www.cwillow.com/images/advertisement/p1/20170531052148045817.jpg");
//        articlescrap.setShow_time(now);
//        articlescrap.setStart_time("公元前200年");
//        articlescrap.setStatus(UpDown_Status.DOWN);
//        articlescrap.setSub_content("test sub_content");
//        articlescrap.setTitle("test title 2");
//        articlescrap.setType(Articlescrap_Type.FOREIGN_HISTORY);
//        articlescrap.setUpdate_time(now);
//        articlescrapMapper.saveOrUpdateArticlescrap(articlescrap);
        
//        Articlescrap articlescrap=articlescrapMapper.getArticlescrapIndex("AVyaUFdogmDrd-ExdK6S");
//        System.out.println(JSON.toJSON(articlescrap));
        
//        ArticlescrapCondtion condition = new ArticlescrapCondtion();
//        List<Articlescrap> list=articlescrapMapper.listArticlescrap(condition);
//        System.out.println(JSON.toJSONString(list));
        
//          articlescrapMapper.deleteById("AVylZSUuGrpiw6YwpZgQ");
        
//        articlescrapMapper.updateArticlescrapRecommend("AVyp6MqoUnl0U38aB3kw", 1, UpDown_Status.UP);
//         List<Articlescrap> recommends=articlescrapMapper.listRecommend(null);
//         System.out.println(JSON.toJSONString(recommends));
        return mv;  
    } 
	
}
