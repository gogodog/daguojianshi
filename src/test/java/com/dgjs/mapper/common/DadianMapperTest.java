package com.dgjs.mapper.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class DadianMapperTest {

	@Autowired
	DadianMapper dadianMapper;
	
	@Test
	public void testCountPageDocIds(){
		int count = dadianMapper.countByCondtion(null,"10336266");
		System.out.println("count="+count);
	}
}
