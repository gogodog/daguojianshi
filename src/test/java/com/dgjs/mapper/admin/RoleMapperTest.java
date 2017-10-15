package com.dgjs.mapper.admin;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.persistence.Role;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class RoleMapperTest {

	@Autowired
	RoleMapper roleMapper;
	
	@Test
	public void testSelectByIds(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		List<Role> l=roleMapper.selectByIds(list);
		System.out.println(JSON.toJSONString(l,true));
	}
}
