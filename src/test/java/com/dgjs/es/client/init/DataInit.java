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
import com.dgjs.es.mapper.content.PendingMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.TIME_DEGREE;
import com.dgjs.model.persistence.condition.DraftCondition;
import com.dgjs.model.persistence.condition.PendingCondition;
import com.dgjs.service.content.PendingService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class DataInit {

	 final static String index = "dp_v4";
		
	 @Autowired
	 ESTransportClient transportClient;
	 
	 @Autowired
	 DraftMapper draftMapper;
	 
	 @Autowired
	 ArticlescrapMapper nArticlescrapMapper;
	 
	 @Autowired
	 PendingMapper pendingMapper;
	 
	 @Autowired
	 PendingService pendingService;
	 
	 /* ================================== draft begin ==================================*/
	 @Test
	 public void testSaveDraft(){
		 Date date = new Date();
		 Draft draft = new Draft();
		 draft.setAuthor("joy测试");
		 draft.setBegin_time(20170711);
		 draft.setContent("joy测试 content123");
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
		 draftMapper.getContent("AV3Zlgrx76afZAVcqAsj");
	 }
	 
	 /* ==================================  draft end  ================================== */
	 
	 /* ================================== pending begin ==================================*/
	 @Test
	 public void savePending(){
		 Date date = new Date();
		 Pending pending = new Pending();
		 pending.setAuthor("joy测试 a111");
		 pending.setBegin_time(20170711);
		 pending.setContent("joy测试 content pending a111");
		 pending.setCreate_time(date);
		 String[] keywords = {"draft keywords"};
		 pending.setKeywords(keywords);
		 pending.setPic_num(1);
		 pending.setPictures(keywords);
		 pending.setSub_content("joy测试 sub_content");
		 pending.setTime_degree(TIME_DEGREE.DAY);
		 pending.setTitle("joy测试 title");
		 pending.setType(Articlescrap_Type.AFFAIRS);
		 pending.setUpdate_time(date);
		 
		 pending.setStatus(Pending_Status.AUDIT_PENDING);
		 int flag = pendingMapper.savePending(pending);
		 System.out.println(flag);
	 }
	 
	 @Test
	 public void selectById(){
		 Pending pending = pendingMapper.selectById("AV3WCNPB76afZAVcqAsi");
		 System.out.println(JSON.toJSONString(pending, true));
	 }
	 
	 @Test
	 public void testAudit() throws Exception{
		 int flag = pendingMapper.audit("AV3wJ79y76afZAVcqAss", Pending_Status.PUBLISH_PENDING, 1, "重复内容");
	     System.out.println(flag);
	 }
	 
	 @Test
	 public void testPublish() throws Exception{
		 Date now = new Date();
//		 Pending pending = pendingMapper.publish("AV3WCNPB76afZAVcqAsi", 1, now, 1001, now);
//		 System.out.println(JSON.toJSONString(pending, true));
		 int flag = pendingService.publish("AV3wJ79y76afZAVcqAss", 1, now, 1001, now);
		 System.out.println(flag);
	 }
	 
	 @Test
	 public void listPending(){
		 PendingCondition condition = new PendingCondition();
		 condition.setStatus(Pending_Status.PUBLISHED);
		 PageInfoDto<Pending> pageinfo = pendingMapper.listPending(condition);
		 System.out.println(JSON.toJSONString(pageinfo, true));
	 }
	 /* ==================================  pending end  ==================================*/
	 
	 @Test
	 public void testDelete(){
		 int flag=nArticlescrapMapper.deleteById("AV3XINsziB8TaXhbksdM");
		 System.out.println(flag);
	 }
}
