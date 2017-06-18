package com.dgjs.mapper.common;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.persistence.result.PagedocidsCountResult;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class DadianMapperTest {

	@Autowired
	DadianMapper dadianMapper;
	
	@Test
	public void testCountPageDocIds(){
		int count = dadianMapper.pageIdCount("10336266");
		System.out.println("count="+count);
	}
	
	@Test
	public void testPagedocidsCount(){
		List<PagedocidsCountResult> list=dadianMapper.pagedocidsCount(Arrays.asList("21","20","19"));
		System.out.println(JSON.toJSON(list));
	}
}
