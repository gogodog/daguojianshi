package com.dgjs.es.client.init;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.es.client.ESTransportClient;
import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.es.mapper.content.DraftMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.TIME_DEGREE;
import com.dgjs.model.persistence.condition.DraftCondition;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class DataInit {

	 final static String index = "dgjs_v4";
		
	 @Autowired
	 ESTransportClient transportClient;
	 
	 @Autowired
	 DraftMapper draftMapper;
	 
	 @Autowired
	 ArticlescrapMapper articlescrapMapper;
	 
	 @Test
	 public void testSaveDraft(){
		 Date date = new Date();
		 Draft draft = new Draft();
		 draft.setAuthor("joy测试");
		 draft.setBegin_time(20170711);
		 draft.setContent("joy测试 content");
		 draft.setCreate_time(date);
		 String[] keywords = {"draft keywords"};
		 draft.setKeywords(keywords);
		 draft.setPic_num(1);
		 draft.setPictures(keywords);
		 draft.setSub_content("joy测试 sub_content");
		 draft.setTime_degree(TIME_DEGREE.DAY);
		 draft.setTitle("joy测试 title");
		 draft.setType(Articlescrap_Type.AFFAIRS);
		 draft.setUpdate_time(date);
		 int flag = draftMapper.saveDraft(draft);
		 System.out.println(flag);
	 }
	 
	 @Test
	 public void testSelectById(){
		 Draft draft = draftMapper.selectById("AV23cJS0iB8TaXhbksdI");
		 System.out.println(JSON.toJSONString(draft, true));
	 }
	 
	 @Test
	 public void testListDrafts(){
		 DraftCondition condition = new DraftCondition();
		 condition.setCurrentPage(1);
		 condition.setOnePageSize(10);
		 condition.setKeyword("efgh");
		 PageInfoDto<Draft> page=draftMapper.listDrafts(condition);
		 List<Draft> list = page.getObjects();
		 System.out.println(JSON.toJSONString(list, true));
	 }
	 
	 @Test
	 public void testupdateDraft() throws Exception{
		 Date date = new Date();
		 Draft draft = new Draft();
		 draft.setId("AV23cJS0iB8TaXhbksdI");
		 draft.setAuthor("joy测试");
		 draft.setBegin_time(20170711);
		 draft.setContent("joy测试 content efgh");
		 draft.setCreate_time(date);
		 String[] keywords = {"draft keywords"};
		 draft.setKeywords(keywords);
		 draft.setPic_num(1);
		 draft.setPictures(keywords);
		 draft.setSub_content("joy测试 sub_content");
		 draft.setTime_degree(TIME_DEGREE.DAY);
		 draft.setTitle("joy测试 title");
		 draft.setType(Articlescrap_Type.AFFAIRS);
		 draft.setUpdate_time(date);
		 draftMapper.updateDraft(draft);
	 }
	 
	 @Test
	 public void testDeleteDraft(){
		 draftMapper.deleteDraft("AV23fYIv76afZAVcqApY");
	 }
	 
	 @Test
	 public void testGetContent(){
		 draftMapper.getContent("AV23cJS0iB8TaXhbksdI");
	 }
}
