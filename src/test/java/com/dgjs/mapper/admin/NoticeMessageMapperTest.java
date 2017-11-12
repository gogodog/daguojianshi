package com.dgjs.mapper.admin;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.enums.Read_Status;
import com.dgjs.model.persistence.NoticeMessage;
import com.dgjs.model.persistence.condition.NoticeMessageCondition;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class NoticeMessageMapperTest {

	@Autowired
	NoticeMessageMapper mapper;
	
	@Test
	public void testSave(){
		NoticeMessage noticeMessage = new NoticeMessage();
		noticeMessage.setAdmin_id(1);
		noticeMessage.setMessage("test message");
		noticeMessage.setStatus(Read_Status.UNREAD);
		int flag = mapper.save(noticeMessage);
		System.out.println(flag);
	}
	
	@Test
	public void testGetByAdminId(){
		NoticeMessageCondition condition = new NoticeMessageCondition();
		condition.setAdminId(1);
		List<NoticeMessage> list = mapper.getByAdminId(condition);
		System.out.println(JSON.toJSONString(list));
	}
	
	@Test
	public void readMessage(){
		int flag = mapper.readMessage(Arrays.asList(1L));
		System.out.println(flag);
	}
}
