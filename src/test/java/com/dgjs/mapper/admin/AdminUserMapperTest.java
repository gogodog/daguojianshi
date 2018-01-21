package com.dgjs.mapper.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.enums.Admin_Source;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.AdminUser;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.result.AdminUserResult;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class AdminUserMapperTest {

	 @Autowired
	 AdminUserMapper mapper;
	 
	 @Test
	 public void testSave(){
		 AdminUser adminUser = new AdminUser();
		 adminUser.setRole_id(1);
		 adminUser.setSource(Admin_Source.WEIXIN);
		 adminUser.setStatus(UpDown_Status.UP);
		 adminUser.setUsername("dgjs");
		 int flag = mapper.save(adminUser);
		 System.out.println(flag);
	 }
	 
	 @Test
	 public void testSelectById(){
		 AdminUser adminUser = mapper.selectById(1);
		 System.out.println(JSON.toJSONString(adminUser, true));
	 }
	 
	 @Test
	 public void testUpdate(){
		 AdminUser adminUser = new AdminUser();
		 adminUser.setUsername("dgjs");
		 adminUser.setStatus(UpDown_Status.DOWN);
		 adminUser.setId(1);
		 int flag = mapper.update(adminUser);
		 System.out.println(flag);
	 }
	 
	 @Test
	 public void testList(){
		 AdminUserCondition condition = new AdminUserCondition();
//		 condition.setStatus(UpDown_Status.DOWN);
//		 condition.setSource(1);
		 List<AdminUserResult> list=mapper.list(condition);
		 System.out.println(JSON.toJSONString(list, true));
	 }
	 
	 @Test
	 public void testBatchSave(){
		 Date now = new Date();
		 AdminUser adminUser1 = new AdminUser();
		 adminUser1.setRole_id(1);
		 adminUser1.setSource(Admin_Source.WEIXIN);
		 adminUser1.setStatus(UpDown_Status.UP);
		 adminUser1.setUsername("gy1");
		 adminUser1.setCreate_time(now);
		 adminUser1.setUpdate_time(now);
		 
		 AdminUser adminUser2 = new AdminUser();
		 adminUser2.setRole_id(1);
		 adminUser2.setSource(Admin_Source.WEIXIN);
		 adminUser2.setStatus(UpDown_Status.UP);
		 adminUser2.setUsername("wjd1");
		 adminUser2.setCreate_time(now);
		 adminUser2.setUpdate_time(now);
		 
		 List<AdminUser> list = new ArrayList<AdminUser>();
		 list.add(adminUser1);
		 list.add(adminUser2);
		 int flag = mapper.batchSave(list);
		 System.out.println(flag);
	 }
	 
	 
}
