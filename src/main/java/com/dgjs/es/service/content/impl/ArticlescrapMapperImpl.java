package com.dgjs.es.service.content.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.dgjs.es.client.ESTransportClient;
import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.es.ArticlescrapEs;
import com.dgjs.model.es.RecommendEs;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.utils.DateUtils;

@Service("cArticlescrapMapper")
public class ArticlescrapMapperImpl implements ArticlescrapMapper{

	@Autowired
	ESTransportClient transportClient;
	
	final static String index = "dgjs";
	
	final static String type = "articlescrap";
	
	@SuppressWarnings("deprecation")
	@Override
	public int saveOrUpdateArticlescrap(Articlescrap articlescrap)  throws Exception{
	    TransportClient client=transportClient.getObject();
	    if(articlescrap == null)
			return 0;
	    try{
	       IndexRequestBuilder indexRequestBuilder;
	       if(StringUtils.isEmpty(articlescrap.getEsId())){
	    	   indexRequestBuilder =client.prepareIndex(index, type);
	       }else{
	    	   indexRequestBuilder =client.prepareIndex(index, type,articlescrap.getEsId());
	       }
		   IndexResponse response = indexRequestBuilder.setSource(ArticlescrapEs.ConvertToEs(articlescrap).toString()).execute().actionGet(); 
		   return StringUtils.isEmpty(response.getId())?0:1;
	    }catch(Exception e){
		  e.printStackTrace();
	    }
		return 0;
	}
	
	 @Override
 	public Articlescrap getArticlescrapIndex(String id) throws Exception {
		TransportClient client=transportClient.getObject();
 		GetResponse response = client.prepareGet(index, type , id).get();
 		ArticlescrapEs articlescrapEs=JSON.parseObject(response.getSourceAsString(), ArticlescrapEs.class);
 		return ArticlescrapEs.ConvertToVo(articlescrapEs);
 	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Articlescrap> listArticlescrap(ArticlescrapCondtion condition) throws Exception{
		BoolQueryBuilder boolBuilder = new BoolQueryBuilder();
		if(condition!=null){
			if(!StringUtils.isEmpty(condition.getTitle())){
				boolBuilder.must(QueryBuilders.termQuery("title", condition.getTitle()));
			}
			if(!StringUtils.isEmpty(condition.getAuthor())){
				boolBuilder.must(QueryBuilders.termQuery("author", condition.getAuthor()));
			}
			if(condition.getStatus()!=null){
				boolBuilder.must(QueryBuilders.termQuery("status", condition.getStatus().getKey()));
			}
			if(condition.getType()!=null){
				boolBuilder.must(QueryBuilders.termQuery("type", condition.getType().getKey()));
			}
			if(condition.getShowTimeFrom()!=null){
				boolBuilder.must(QueryBuilders.rangeQuery("show_time").gte(DateUtils.parseStringFromDate(condition.getShowTimeFrom())));
			}
			if(condition.getShowTimeTo()!=null){
				boolBuilder.must(QueryBuilders.rangeQuery("show_time").lte(DateUtils.parseStringFromDate(condition.getShowTimeTo())));
			}
		}
		TransportClient client=transportClient.getObject();
		SearchRequestBuilder responsebuilder = client.prepareSearch(index).setTypes(type);
		String[] fields={"title","show_time","status","type","author","create_time","update_time","sub_content","show_picture","start_time"};
		responsebuilder.setQuery(boolBuilder);
		if(!StringUtils.isEmpty(condition.getSort())){
			Map<String,SortOrder> m =JSON.parseObject(condition.getSort(), Map.class);
			for(String key:m.keySet()){
				responsebuilder.addSort(key,m.get(key));
			}
		}
		responsebuilder.storedFields(fields);
		SearchResponse myresponse = responsebuilder.setFrom((condition.getCurrentPage()-1)*condition.getOnePageSize()).
				setSize(condition.getOnePageSize()).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		if(hits.getTotalHits()>0){
			List<Articlescrap> list = new ArrayList<Articlescrap>();
			for (int i = 0; i < hits.getHits().length; i++) {
				Map<String,SearchHitField> map=hits.getHits()[i].getFields();
				Articlescrap articlescrap=getArticlescrap(map);
				articlescrap.setEsId(hits.getHits()[i].getId());
				list.add(articlescrap);
			}
			return list;
		}
		return null;
	}
	
	private Articlescrap getArticlescrap(Map<String,SearchHitField> map){
		Articlescrap articlescrap = new Articlescrap();
		articlescrap.setAuthor(map.get("author").value());
		articlescrap.setCreate_time(DateUtils.parseDateFromString(map.get("create_time").value()));
		articlescrap.setShow_picture(map.get("show_picture").value());
		articlescrap.setShow_time(DateUtils.parseDateFromString(map.get("show_time").value()));
		articlescrap.setStart_time(map.get("start_time").value());
		articlescrap.setSub_content(map.get("sub_content").value());
		articlescrap.setUpdate_time(DateUtils.parseDateFromString(map.get("update_time").value()));
		articlescrap.setTitle(map.get("title").value());
		articlescrap.setType(Articlescrap_Type.valueOf(Integer.parseInt(map.get("type").value())));
		articlescrap.setStatus(UpDown_Status.valueOf(Integer.parseInt(map.get("status").value())));
		return articlescrap;
	}

	@Override
	public int deleteById(String id) throws Exception{
		TransportClient client=transportClient.getObject();
		DeleteResponse response=client.prepareDelete(index, type, id).execute().actionGet();
		return StringUtils.isEmpty(response.getId())?0:1;
	}

	@Override
	public int updateArticlescrapRecommend(String id, int sort, UpDown_Status status) throws Exception{
		TransportClient client=transportClient.getObject();
		RecommendEs recommend = new RecommendEs();
		recommend.setSort(sort);
		recommend.setStatus(status.getKey());
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("recommend", JSON.toJSON(recommend));
	    StringBuilder str=new StringBuilder("ctx._source.recommend=params.recommend;");
	    Script script = new Script(ScriptType.INLINE,"painless",str.toString(),map);
	    UpdateResponse response=client.prepareUpdate(index, type, id).setScript(script).execute().actionGet();
	    return StringUtils.isEmpty(response.getId())?0:1;
	}

	@Override
	public List<Articlescrap> listRecommend(UpDown_Status status)  throws Exception{
		TransportClient client=transportClient.getObject();
	    SearchRequestBuilder responsebuilder = client.prepareSearch(index).setTypes(type);
	    BoolQueryBuilder boolBuilder = new BoolQueryBuilder();
	    BoolQueryBuilder innerBoolBuilder = null;
	    if(status == null){
	    	 innerBoolBuilder = QueryBuilders.boolQuery().
	    			must(QueryBuilders.rangeQuery("recommend.status").gt(-1));
	    }else{
	    	 innerBoolBuilder = QueryBuilders.boolQuery().
	    				must(QueryBuilders.termQuery("recommend.status", status.getKey()));
	    }
	    NestedQueryBuilder nestedQuery = new NestedQueryBuilder("recommend",innerBoolBuilder ,ScoreMode.Min); 
	    boolBuilder.filter(nestedQuery);
	    responsebuilder.setQuery(boolBuilder); //设置查询条件
	    SearchResponse myresponse = responsebuilder.execute().actionGet();
	    SearchHits hits = myresponse.getHits();
	    if(hits.getTotalHits()>0){
	    	List<Articlescrap> list = new ArrayList<Articlescrap>();
	    	for (int i = 0; i < hits.getHits().length; i++) {
	    		String value=hits.getHits()[i].getSourceAsString();
	    		Articlescrap articlescrap=ArticlescrapEs.ConvertToVo(JSON.parseObject(value, ArticlescrapEs.class));
	    		list.add(articlescrap);
	    	}
	    	return list;
	    }		
		return null;
	}
	
}
