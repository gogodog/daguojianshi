package com.dgjs.mapper.content;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.RecommedArticlescrap;
import com.dgjs.model.persistence.condition.RecommedArticlescrapCondition;
import com.dgjs.model.persistence.enhance.RecommedArticlescrapEnhance;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class RecommedArticlescrapMapperTest {

	@Autowired
	RecommedArticlescrapMapper mapper;
	
	@Test
	public void testSave(){
		RecommedArticlescrap recommedArticlescrap=new RecommedArticlescrap();
		recommedArticlescrap.setArticlescrap_id(3l);
		recommedArticlescrap.setSort(1);
		recommedArticlescrap.setStatus(UpDown_Status.DOWN);
		int flag=mapper.save(recommedArticlescrap);
		System.out.println("插入"+flag+"条");
	}
	
	@Test
	public void testList(){
		RecommedArticlescrapCondition condition=new RecommedArticlescrapCondition();
		condition.setStatus(UpDown_Status.DOWN);
		condition.setTitle("joy测试标题");
		List<RecommedArticlescrapEnhance> list=mapper.list(condition);
		System.out.println(JSON.toJSONString(list, true));
	}
	
	@Test
	public void testDeleteById(){
		int flag=mapper.deleteById(3l);
        System.out.println("删除"+flag+"条");
	}
}
