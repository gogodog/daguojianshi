package com.dgjs.mapper.content;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgjs.model.persistence.Comments;
import com.dgjs.utils.IdsUtils;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class CommentsMapperTest {
	
	@Autowired
	CommentsMapper mapper;
	
	@Test
	public void testSave(){
		Comments comments = new Comments();
//		comments.setArticlescrap_id(6l);
		comments.setComment("测试评论");
		comments.setComment_id(null);
		comments.setComment_name("joy");
		comments.setComment_time(new Date());
		comments.setId(IdsUtils.getUuId());
		comments.setIp_address("127.0.0.1");
		comments.setIs_show(true);
		comments.setParent_id(null);
		int flag=mapper.save(comments);
		System.out.println(flag);
	}
	
	@Test
	public void testUpdate(){
		Comments comments = new Comments();
		comments.setId("375dc12f-1fc8-4856-b7bf-f3e6b70c03c8");
		comments.setC_desc("ceshi");
		comments.setIs_show(true);
		int f=mapper.update(comments);
		System.out.println(f);
	}

}
