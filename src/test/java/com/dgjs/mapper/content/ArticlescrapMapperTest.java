package com.dgjs.mapper.content;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class ArticlescrapMapperTest {

	@Autowired
	ArticlescrapMapper mapper;
	
	@Test
	public void testListArticlescrap(){
		PageInfoDto<Articlescrap> pageinfo=mapper.listArticlescrap(new ArticlescrapCondtion());
		System.out.println(JSON.toJSONString(pageinfo, true));
	}

	@Test
	public void testBulkUpdateStatus(){
		String[] ids = {"AVzZrY1I9b4MAjksb_WD","AVzZrYlm9b4MAjksb_WA"};
		List<Articlescrap> list=mapper.getArticlescrapByIds(ids);
		for(Articlescrap articlescrap:list){
			Articlescrap a = mapper.getArticlescrap(articlescrap.getId());
			articlescrap.setContent(a.getContent());
		}
		mapper.bulkUpdateStatus(list, Articlescrap_Status.INIT);
	}
}
