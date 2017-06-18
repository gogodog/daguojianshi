package com.dgjs.es.client.init;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dgjs.es.client.ESTransportClient;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class EsInit {

    final static String index = "dgjs";
	
	final static String type = "articlescrap";
	
	@Autowired
	ESTransportClient transportClient;
	
	@Test
	public void testInitTable() throws Exception{
		initArticlescrap(transportClient.getObject());
	}
	
	private void initArticlescrap(TransportClient client) throws IOException {
		createIndex(client,index);
		XContentBuilder builder=XContentFactory.jsonBuilder()
				.startObject()
				.startObject(type)
				.startObject("properties")
				.startObject("title").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
				.startObject("show_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("type").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("status").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("author").field("type", "keyword").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("create_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "yes").field("index", "not_analyzed").endObject()
		        .startObject("update_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "yes").field("index", "not_analyzed").endObject()
		        .startObject("start_time").field("type", "keyword").field("store", "yes").field("index", "not_analyzed").endObject()
		        .startObject("show_picture").field("type", "keyword").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("sub_content").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
				.startObject("content").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
				.startObject("recommend").field("type", "nested")
				.startObject("properties")
				.startObject("sort").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("status").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
				.endObject()
				.endObject()
				.endObject()
				.endObject()
				.endObject();
	   PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(builder); 
	   client.admin().indices().putMapping(mapping).actionGet();
	}
	
	private void createIndex(TransportClient client,String index) {
		CreateIndexRequest request = new CreateIndexRequest(index);
		IndicesAdminClient indices  = client.admin().indices();
		indices.create(request).actionGet();
	}
}
