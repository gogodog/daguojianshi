package com.dgjs.test.es;

import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.search.SearchHit;

/**
 * 测试类
 * @ClassName: TestEsClient 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author cott.wen
 * @date 2017年3月8日 下午3:02:53 
 *
 */
public class TestEsClient {
	public static void main(String[] args) {
		TestEsClient.testGet();
	}
	
	static void testCreateIndex(){
		String result = ToolsDaoImpl.prepareCreateByIndex$type$json("flower", "spring", "{\"en-name\":\"rhododendron\",\"cn-name\":\"杜鹃花\",\"type\":\"stem\"}");
		System.out.println(result);
	}
	
	static void testGet(){
		SearchHit[] result = ToolsDaoImpl.prepareGetByIndexType("flower", "spring");
		System.out.println(result.length);
	}
}
