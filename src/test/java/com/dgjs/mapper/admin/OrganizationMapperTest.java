package com.dgjs.mapper.admin;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Organization;
import com.dgjs.model.persistence.condition.OrganizationCondition;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class OrganizationMapperTest {

	@Autowired
	OrganizationMapper mapper;
	
	@Test
	public void testSave(){
		Organization o = new Organization();
		o.setOname("大国简史");
		o.setNumber(2);
		o.setStatus(UpDown_Status.UP);
		o.setSummary("紫金云讯公司");
		int flag=mapper.save(o);
		System.out.println(flag);
	}
	
	@Test
	public void testSelectById(){
		Organization o=mapper.selectById(1);
		System.out.println(JSON.toJSONString(o, true));
	}
	
	@Test
	public void testUpdate(){
		Organization o = new Organization();
		o.setOname("大国简史");
		o.setNumber(2);
		o.setStatus(UpDown_Status.UP);
		o.setSummary("紫金云讯");
		o.setId(1);
		int flag=mapper.update(o);
		System.out.println(flag);
	}
	
	@Test
	public void testList(){
		OrganizationCondition condition = new OrganizationCondition();
		condition.setStatus(UpDown_Status.UP);
		List<Organization>list=mapper.list(condition);
		System.out.println(JSON.toJSONString(list, true));
	}
}
