package com.dgjs.test.es;

import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 * @ClassName: ToolsDaoImpl 
 * @Description: ES工具类
 * @author cott.wen
 * @date 2017年3月8日 下午2:52:21 
 */
public class ToolsDaoImpl {
	
	/**
	 * 通过索引插入数据
	 * @param index
	 * @param type
	 * @param json
	 * @return
	 */
	public static String prepareCreateByIndex$type$json(String index, String type, String json) {
		return EsClient.getInstence().prepareIndex(index, type).setSource(json).get().toString();
	}
	
	/**
	 * 通过索引，类型，id值查询
	 * 类型可以为空
	 * @param index
	 * @param type
	 * @param id
	 * @return
	 */
	public static String prepareGetByIndex$type$id(String index, String type, String id){
		GetResponse response = EsClient.getInstence().prepareGet(index, type, id).execute().actionGet();
		return response.getSourceAsString();
	}
	
	/**
	 * 条件查询
	 * @param index
	 * @param id
	 * @param types
	 * @return
	 */
	public static SearchHit[] prepareGetByIndexType(String index, String... types){
		SearchResponse response = EsClient.getInstence().prepareSearch(index).setTypes(types).execute().actionGet();
		return response.getHits().getHits();
	}
	
	public static SearchHit[] prepareGetIndexByParams(String index, Map<String, Object> params){
		QueryBuilder queryBuilder = ToolsDaoImpl.getQB(params);
		SearchResponse response = EsClient.getInstence().prepareSearch(index).setQuery(queryBuilder).execute().actionGet();			
		return response.getHits().getHits();
	}
	
	public static SearchHit[] prepareGetIndexTypesByParams(String index, Map<String, Object> params, String... types){
		QueryBuilder queryBuilder = ToolsDaoImpl.getQB(params);
		SearchResponse response = EsClient.getInstence().prepareSearch(index).setTypes(types).setQuery(queryBuilder).execute().actionGet();			
		return response.getHits().getHits();
	}
	
	public static QueryBuilder getQB(Map<String, Object> params){
		if(params.isEmpty()){
			return null;
		}
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
		for (String key : params.keySet()) {
			boolQueryBuilder.must(QueryBuilders.termQuery(key, params.get(key)));
		}
		return boolQueryBuilder;
	}
}
