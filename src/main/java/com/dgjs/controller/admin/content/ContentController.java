package com.dgjs.controller.admin.content;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.content.ArticlescrapService;

@Controller
@RequestMapping("/admin/ctnt")
public class ContentController {

	@Autowired
	ArticlescrapService articlescrapSerivce;
	
	@Autowired
	ArticlescrapMapper articlescrapMapper;
	
	@RequestMapping("/contentList")
    public ModelAndView helloWord(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("admin/content/index");  
        mv.addObject("title", "Spring MVC And Freemarker");  
        mv.addObject("content", " Hello world ， test my first spring mvc ! ");  
        
        
        
        ArticlescrapCondtion condition = new ArticlescrapCondtion();
        condition.setOnePageSize(40);
        PageInfoDto<Articlescrap> page=articlescrapSerivce.listArticlescrap(condition);
        List<Articlescrap> list=page.getObjects();
        StringBuilder str = new StringBuilder();
        for(Articlescrap a:list){
        	str.append("\""+a.getId()+"\",");
        }
        System.out.println(str);
        
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
//        articlescrap.setType(Arrays.asList(Articlescrap_Type.UNOFFICIAL,Articlescrap_Type.PERSON));
//        articlescrap.setUpdate_time(now);
//        articlescrap.setVisits(100l);
//        articlescrapSerivce.saveArticlescrap(articlescrap);
//        articlescrapMapper.saveOrUpdateArticlescrap(articlescrap);
        
//        Articlescrap articlescrap=articlescrapMapper.getArticlescrapIndex("AVyaUFdogmDrd-ExdK6S");
//        System.out.println(JSON.toJSON(articlescrap));
        
//        ArticlescrapCondtion condition = new ArticlescrapCondtion();
//        List<Articlescrap> list=articlescrapMapper.listArticlescrap(condition);
//        System.out.println(JSON.toJSONString(list));
        
//          articlescrapMapper.deleteById("AVzZd5Y-qMQTX7aOp80D");
        
//        articlescrapMapper.updateArticlescrapRecommend("AVyp6MqoUnl0U38aB3kw", 1, UpDown_Status.UP);
//         List<Articlescrap> recommends=articlescrapMapper.listRecommend(null);
//         System.out.println(JSON.toJSONString(recommends));
//        ArticlescrapCondtion condition = new ArticlescrapCondtion();
//        condition.setOnePageSize(20);
//        PageInfoDto<Articlescrap> page=articlescrapSerivce.listArticlescrap(condition);
//        List<Articlescrap> readList=page.getObjects();
//        for(Articlescrap articlescrap:readList){
//        	ArticlescrapEsX a=articlescrapMapper.getArticlescrapIndex1(articlescrap.getId());
//        	articlescrap.setType(Arrays.asList(Articlescrap_Type.valueOf(a.getType())));
//        	articlescrap.setContent(a.getContent());
//        	articlescrapMapper.saveArticlescrap(articlescrap);
//        }
//        System.out.println("success ===================");
//        System.out.println(JSON.toJSONString(page.getObjects()));
        return mv;  
    } 
	
}
