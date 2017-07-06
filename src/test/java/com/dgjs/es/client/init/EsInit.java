package com.dgjs.es.client.init;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.es.client.ESTransportClient;
import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.es.ArticlescrapEs;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.content.ArticlescrapService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class EsInit {

    final static String index = "dgjs_v1";
	
	final static String type = "articlescrap_v1";
	
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
				.startObject("visits").field("type", "long").field("store", "yes").field("index", "not_analyzed").endObject()
				.startObject("keywords").field("type", "keyword").field("store", "yes").field("index", "not_analyzed").endObject()
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
	
	@Test
	public void testUpsert() throws  Exception{
		TransportClient client=transportClient.getObject();
		String[] ids={"AVzZrZRC9b4MAjksb_WL","AVzZrY1I9b4MAjksb_WD","AVzZrZMX9b4MAjksb_WI","AVzZrYlm9b4MAjksb_WA","AVzZrZOM9b4MAjksb_WJ","AVzZrYoE9b4MAjksb_WB","AVzZrY769b4MAjksb_WG","AV0DmzcMqMQTX7aOp80m","AVzZrY6J9b4MAjksb_WF","AVzZrYyi9b4MAjksb_WC","AVzZrZPu9b4MAjksb_WK","AV0H7njhqMQTX7aOp80q","AVzZrY-z9b4MAjksb_WH","AVzZrY3Z9b4MAjksb_WE"};
//		String t_index="dgjs_test";
//		String t_type="dgjs_type";
//		createIndex(client,t_index);
//		
//		XContentBuilder builder=XContentFactory.jsonBuilder()
//				.startObject()
//				.startObject(t_type)
//				.startObject("properties")
//				.startObject("user").field("type", "text").field("store", "true").field("index", "not_analyzed").endObject()
//				.startObject("updatetime").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").field("store", "true").field("index", "not_analyzed").endObject()
//				.startObject("number").field("type", "integer").field("store", "true").field("index", "not_analyzed").endObject()
//				.startObject("message").field("type", "text").field("store", "true").field("index", "not_analyzed").endObject()
//		        .endObject()
//				.endObject()
//				.endObject();
//	   PutMappingRequest mapping = Requests.putMappingRequest(t_index).type(t_type).source(builder); 
//	   client.admin().indices().putMapping(mapping).actionGet();
//		
//		     client.prepareIndex("dgjs_test", "dgjs_type", "1")
//		        .setSource(XContentFactory.jsonBuilder()
//		                    .startObject()
//		                        .field("user", "kimchy")
//		                        .field("updatetime", "2017-07-04 10:26:00")
//		                        .field("message", "trying out Elasticsearch")
//		                        .field("number", 1)
//		                    .endObject()
//		                  )
//		        .get();
		
		
	
	 	
		for(String id:ids){
			  GetResponse resp = client.prepareGet(index, type , id).get();
			  Map map=resp.getSourceAsMap();
			  map.get("");
			  IndexRequestBuilder indexRequestBuilder =client.prepareIndex("dgjs_v2", "articlescrap_v2");
		}
		

	  
	  
	  
	  
		
//      UpdateRequest updateRequest = new UpdateRequest("dgjs_test", "dgjs_type", "1")
//       .doc(XContentFactory.jsonBuilder()
//           .startObject()
//               .field("updatetime", "2017-07-04 10:30:00")
//               .field("number", number+1)
//           .endObject());
//      client.update(updateRequest).get();
//      
//      resp = client.prepareGet("dgjs_test", "dgjs_type" , "1").get();
//      System.out.println(resp.getSourceAsString());
      
//      SearchRequestBuilder responsebuilder = client.prepareSearch("dgjs_test").setTypes("dgjs_type");
//      String[] fields = {"updatetime","number"};
//      responsebuilder.storedFields(fields);
//      responsebuilder.setQuery(QueryBuilders.matchAllQuery());
//      SearchResponse myresponse = responsebuilder.setExplain(true).execute().actionGet();
//      SearchHits hits = myresponse.getHits();
//      for (int i = 0; i < hits.getHits().length; i++) {
//			Map<String,SearchHitField> map=hits.getHits()[i].getFields();
//			System.out.println("number:"+JSON.toJSONString(map));
//			System.out.println("sourceAsString:"+hits.getHits()[i].getSourceAsString());
//		}
	}
}
