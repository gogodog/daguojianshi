package com.dgjs.mapper.ad;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.persistence.Advertisement;
import com.dgjs.model.persistence.condition.AdvertisementCondtion;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-mybatis.xml") 
public class AdvertisementMapperTest {

	@Autowired
	AdvertisementMapper mapper;
	
	@Test
	public void testListAdvertisement(){
		List<Advertisement> list = mapper.listAdvertisement(new AdvertisementCondtion());
		System.out.println(JSON.toJSONString(list, true));
	}
}
