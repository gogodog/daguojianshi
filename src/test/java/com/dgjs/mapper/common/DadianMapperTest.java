package com.dgjs.mapper.common;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.es.mapper.common.DadianMapper;
import com.dgjs.model.persistence.result.PagedocidsCountResult;
import com.dgjs.model.result.view.DadianView;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class DadianMapperTest {

//	@Autowired
//	DadianMapperC dadianMapper;
//	
//	@Test
//	public void testCountPageDocIds(){
//		int count = dadianMapper.pageIdCount("10336266");
//		System.out.println("count="+count);
//	}
//	
//	@Test
//	public void testPagedocidsCount(){
//		List<PagedocidsCountResult> list=dadianMapper.pagedocidsCount(Arrays.asList("21","20","19"));
//		System.out.println(JSON.toJSON(list));
//	}
	
	@Autowired
	DadianMapper dadianMapper;
	
	@Test
	public void testInsert(){
		DadianView dadianView = new DadianView();
		dadianView.setPadpcmobile("mobile");
		dadianView.setBrowseversion("11");
		dadianView.setChannel("");
		dadianView.setHeight("525");
		dadianView.setWidth("840");
		dadianView.setIp("127.0.0.1");
		dadianView.setIpcity("北京市");
		dadianView.setIpcountry("中国");
		dadianView.setIpprovince("北京市");
		dadianView.setMac("");
		dadianView.setNote("dgjs-system");
		dadianView.setOs("other");
		dadianView.setPage("index");
		dadianView.setPageadids("2#3#8#9#10#11#12#5#6#7");
		dadianView.setPagedocids("AV2RYOxY76afZAVcqApC");
		dadianView.setPageid("10336267");
		dadianView.setPagetype("detail");
		dadianView.setSendwindowsevent("onload");
		dadianView.setUa("mozilla/5.0 (iphone; cpu iphone os 9_1 like mac os x) applewebkit/601.1.46 (khtml, like gecko) version/9.0 mobile/13b143 safari/601.1 (compatible; baiduspider-render/2.0; +http://www.baidu.com/search/spider.html)");
		dadianView.setUuid("258811dc1c0147fa9f6b3b8fc20ff2d6");
		int flag = dadianMapper.insert(dadianView);
		System.out.println(flag);
	}
	
	@Test
	public void testPageIdCount(){
		long count = dadianMapper.pageIdCount("10336267");
		System.out.println(count);
	}
	
	@Test
	public void testPagedocidsCount(){
		List<PagedocidsCountResult> list = dadianMapper.pagedocidsCount(Arrays.asList("AV2RYOxY76afZAVcqApC"));
		System.out.println(JSON.toJSONString(list));
	}
}
