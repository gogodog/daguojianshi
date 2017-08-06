package com.dgjs.es.client.init;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
public class StructureInit {
	
	    final static String index = "dgjs_v4";
		
		@Autowired
		ESTransportClient transportClient;
		
		@Test
		public void testInitTable() throws Exception{
			initDraft(transportClient.getObject());
		}
		
		private void createIndex(TransportClient client,String index) {
			CreateIndexRequest request = new CreateIndexRequest(index);
			IndicesAdminClient indices  = client.admin().indices();
			indices.create(request).actionGet();
		}
		
		private void initArticlescrap(TransportClient client) throws IOException {
			String type =  "articlescrap_v3";
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
			        .startObject("start_time").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
					.startObject("sub_content").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
					.startObject("content").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
					.startObject("visits").field("type", "long").field("store", "yes").field("index", "not_analyzed").endObject()
					.startObject("keywords").field("type", "keyword").field("store", "yes").field("index", "not_analyzed").endObject()
					.startObject("time_degree").field("type", "integer").field("store", "yes").field("index", "not_analyzed").endObject()
					.startObject("pictures").field("type", "keyword").field("store", "yes").field("index", "not_analyzed").endObject()
					.startObject("pic_num").field("type", "integer").field("index", "not_analyzed").endObject()
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
		
		private void initDraft(TransportClient client) throws IOException {
			String type =  "draft_v4";
			createIndex(client,index);
			List<String> list = Arrays.asList("content");
			XContentBuilder builder=XContentFactory.jsonBuilder()
					.startObject()
					.startObject(type)
					.startObject("_source").field("excludes", list).endObject()
					.startObject("properties")
					.startObject("title").field("type", "text").field("store", "false").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
					.startObject("type").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
					.startObject("author").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
					.startObject("create_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
			        .startObject("update_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
					.startObject("sub_content").field("type", "text").field("store", "false").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
					.startObject("content").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
					.startObject("keywords").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
					.startObject("start_time").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
					.startObject("time_degree").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
					.startObject("pictures").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
					.startObject("pic_num").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
					.startObject("user_id").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
					.endObject()
					.endObject()
					.endObject();
		   PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(builder); 
		   client.admin().indices().putMapping(mapping).actionGet();
		}
		
}
