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
public class NewEsStructureInit {
	
	@Autowired
	ESTransportClient transportClient;
	
	@Test
	public void testInitTable() throws Exception{
//		initDraft(transportClient.getObject());
//		initPending(transportClient.getObject());
		initArticlescrap(transportClient.getObject());
		initLogIndex(transportClient.getObject());
		initPDraft(transportClient.getObject());
	}
	
	private void createIndex(TransportClient client,String index) {
		CreateIndexRequest request = new CreateIndexRequest(index);
		IndicesAdminClient indices  = client.admin().indices();
		indices.create(request).actionGet();
	}

	private void initArticlescrap(TransportClient client) throws IOException {
		String index = "dgjs_v1";
		String type = "articlescrap_v1";
		createIndex(client,index);
		XContentBuilder builder=XContentFactory.jsonBuilder()
				.startObject()
				.startObject(type)
				.startObject("properties")
				.startObject("title").field("type", "text").field("store", "false").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
				.startObject("content").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
				.startObject("show_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("status").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("author").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("create_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
		        .startObject("update_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
		        .startObject("sub_content").field("type", "text").field("store", "false").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
		        .startObject("visits").field("type", "long").field("store", "false").field("index", "not_analyzed").endObject()
		        .startObject("start_time").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("keywords").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("type").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("recommend").field("type", "nested")
				.startObject("properties")
				.startObject("sort").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("status").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.endObject()
				.endObject()
				.startObject("time_degree").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("pictures").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("pic_num").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("user_id").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("draft_id").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("pic_sync_status").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("progress").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("show_pic").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.endObject()
				.endObject()
				.endObject();
	   PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(builder); 
	   client.admin().indices().putMapping(mapping).actionGet();
	}
	
	private void initPDraft(TransportClient client) throws IOException {
		String index = "dp_v1";
		String type =  "draft_v1";
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
				.startObject("pictures").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("start_time").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("time_degree").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("pic_num").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("user_id").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("status").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("audit_user_id").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("audit_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("publish_user_id").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("publish_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("isHaveAudit").field("type", "boolean").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("isHavePublish").field("type", "boolean").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("show_pic").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.endObject()
				.endObject()
				.endObject();
	   PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(builder); 
	   client.admin().indices().putMapping(mapping).actionGet();
	}
	
//	private void initDraft(TransportClient client) throws IOException {
//		String index = "dp_v1";
//		String type =  "draft_v1";
//		createIndex(client,index);
//		List<String> list = Arrays.asList("content");
//		XContentBuilder builder=XContentFactory.jsonBuilder()
//				.startObject()
//				.startObject(type)
//				.startObject("_source").field("excludes", list).endObject()
//				.startObject("properties")
//				.startObject("title").field("type", "text").field("store", "false").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
//				.startObject("content").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
//				.startObject("type").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("author").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("create_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
//		        .startObject("update_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("sub_content").field("type", "text").field("store", "false").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
//				.startObject("keywords").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("start_time").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("time_degree").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("pictures").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("pic_num").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("user_id").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("draft_status").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.endObject()
//				.endObject()
//				.endObject();
//	   PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(builder); 
//	   client.admin().indices().putMapping(mapping).actionGet();
//	}
	
//	private void initPending(TransportClient client) throws IOException {
//		String index = "dp_v1";
//		String type =  "pending_v1";
////		createIndex(client,index);
//		List<String> list = Arrays.asList("content");
//		XContentBuilder builder=XContentFactory.jsonBuilder()
//				.startObject()
//				.startObject(type)
//				.startObject("_source").field("excludes", list).endObject()
//				.startObject("properties")
//				.startObject("title").field("type", "text").field("store", "false").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
//				.startObject("type").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("author").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("create_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
//		        .startObject("update_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("sub_content").field("type", "text").field("store", "false").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
//				.startObject("content").field("type", "text").field("store", "yes").field("index", "analyzed").field("analyzer", "ik_smart").endObject()
//				.startObject("keywords").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("start_time").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("time_degree").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("pictures").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("pic_num").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("user_id").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("status").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("audit_user_id").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("audit_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("audit_desc").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("publish_user_id").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("publish_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("visits").field("type", "long").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("show_time").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("draft_id").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("pic_sync_Status").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.startObject("progress").field("type", "integer").field("store", "false").field("index", "not_analyzed").endObject()
//				.endObject()
//				.endObject()
//				.endObject();
//	   PutMappingRequest mapping = Requests.putMappingRequest(index).type(type).source(builder); 
//	   client.admin().indices().putMapping(mapping).actionGet();
//	}
	
	private void initLogIndex(TransportClient client) throws IOException {
		String log_index = "dgjs_log_v1";
		String type =  "dadian";
		createIndex(client,log_index);
		XContentBuilder builder=XContentFactory.jsonBuilder()
				.startObject()
				.startObject(type)
				.startObject("_source").endObject()
				.startObject("properties")
				.startObject("ctime").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("pagedocids").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("pageid").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("pageadids").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("page").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("pagetype").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("channel").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("os").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("padpcmobile").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("browseversion").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("ipcountry").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("ipprovince").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.startObject("ipcity").field("type", "keyword").field("store", "false").field("index", "not_analyzed").endObject()
				.endObject()
				.endObject()
				.endObject();
	   PutMappingRequest mapping = Requests.putMappingRequest(log_index).type(type).source(builder); 
	   client.admin().indices().putMapping(mapping).actionGet();
	}
}
