package com.dgjs.es.mapper.content.impl;

import java.util.ArrayList;
import java.util.Date;
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
import org.elasticsearch.action.update.UpdateRequest;
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

import com.alibaba.fastjson.JSON;
import com.dgjs.es.client.ESTransportClient;
import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.dto.business.entity.Recommend;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.es.ArticlescrapEs;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.utils.DateUtils;
import com.dgjs.utils.StringUtils;

@Service
public class ArticlescrapMapperImpl implements ArticlescrapMapper{


	@Autowired
	ESTransportClient transportClient;
	
	final static String index = "dgjs_v4";
	
	final static String type = "articlescrap_v4";
	
	@Override
	public Articlescrap getArticlescrapIndex(String id) {
		TransportClient client=transportClient.getClient();
 		GetResponse response = client.prepareGet(index, type , id).get();
 		ArticlescrapEs articlescrapEs=JSON.parseObject(response.getSourceAsString(), ArticlescrapEs.class);
 		articlescrapEs.setContent(getContent(id));
 		Articlescrap articlescrap =  ArticlescrapEs.ConvertToVo(articlescrapEs);
 		articlescrap.setId(id);
 		return articlescrap;
	}

	@Override
	public PageInfoDto<Articlescrap> listArticlescrap(
			ArticlescrapCondtion condition) {
		BoolQueryBuilder boolBuilder = getListQueryBuilder(condition);
		TransportClient client=transportClient.getClient();
		SearchRequestBuilder responsebuilder = client.prepareSearch(index).setTypes(type);
		responsebuilder.setQuery(boolBuilder);
		Map<String, SortOrder> sort = condition.getSort();
		if(sort!=null&&!sort.isEmpty()){
		   for(String key:sort.keySet()){
				responsebuilder.addSort(key,sort.get(key));
			}
		}
		condition.setBeginNum((condition.getCurrentPage()-1)*condition.getOnePageSize());
		SearchResponse myresponse = responsebuilder.setFrom(condition.getBeginNum()).
				setSize(condition.getOnePageSize()).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		if(hits.getTotalHits()>0){
			List<Articlescrap> list = new ArrayList<Articlescrap>();
			for (int i = 0; i < hits.getHits().length; i++) {
				String source = hits.getHits()[i].getSourceAsString();
				ArticlescrapEs articlescrapEs = JSON.parseObject(source, ArticlescrapEs.class);
				Articlescrap articlescrap = ArticlescrapEs.ConvertToVo(articlescrapEs);
				articlescrap.setId(hits.getHits()[i].getId());
				list.add(articlescrap);
			}
		    return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), hits.getTotalHits(), list);
		}
		return null;
	}

	@Override
	public int deleteById(String id) {
		TransportClient client=transportClient.getClient();
		DeleteResponse response=client.prepareDelete(index, type, id).execute().actionGet();
		return StringUtils.isNullOrEmpty(response.getId())?0:1;
	}

	@Override
	public int updateArticlescrapRecommend(String id, Integer sort,
			UpDown_Status status) {
		TransportClient client=transportClient.getClient();
		Recommend recommend = new Recommend();
		if(sort!=null){
			recommend.setSort(sort);
		}
		recommend.setStatus(status.getKey());
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("recommend", JSON.toJSON(recommend));
	    StringBuilder str=new StringBuilder("ctx._source.recommend=params.recommend;");
	    Script script = new Script(ScriptType.INLINE,"painless",str.toString(),map);
	    UpdateResponse response=client.prepareUpdate(index, type, id).setScript(script).execute().actionGet();
	    return StringUtils.isNullOrEmpty(response.getId())?0:1;
	}

	@Override
	public List<Articlescrap> listRecommend(UpDown_Status status) {
		transportClient.refreshIndex(index);
		TransportClient client=transportClient.getClient();
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
	    responsebuilder.addSort("recommend.sort", SortOrder.ASC);
	    SearchResponse myresponse = responsebuilder.execute().actionGet();
	    SearchHits hits = myresponse.getHits();
	    if(hits.getTotalHits()>0){
	    	List<Articlescrap> list = new ArrayList<Articlescrap>();
	    	for (int i = 0; i < hits.getHits().length; i++) {
	    		String value=hits.getHits()[i].getSourceAsString();
	    		Articlescrap articlescrap=ArticlescrapEs.ConvertToVo(JSON.parseObject(value, ArticlescrapEs.class));
	    		articlescrap.setId(hits.getHits()[i].getId());
	    		list.add(articlescrap);
	    	}
	    	return list;
	    }		
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int saveArticlescrap(Articlescrap articlescrap) {
		TransportClient client=transportClient.getClient();
	    if(articlescrap == null)
			return 0;
	    Date date = null;
	    if(articlescrap.getUpdate_time()==null){
	    	articlescrap.setUpdate_time(date==null?date=new Date():date);
	    }
	    if(articlescrap.getCreate_time()==null){
	    	 articlescrap.setCreate_time(date==null?date=new Date():date);
	    }
	    if(articlescrap.getRecommend()==null){
	    	Recommend recommend = new Recommend();
	    	recommend.setSort(-1);
	    	recommend.setStatus(-1);
	    	articlescrap.setRecommend(recommend);
	    }
	    IndexRequestBuilder indexRequestBuilder =client.prepareIndex(index, type);
	    IndexResponse response = indexRequestBuilder.setSource(ArticlescrapEs.ConvertToEs(articlescrap).toString()).execute().actionGet();
	    return StringUtils.isNullOrEmpty(response.getId())?0:1;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int updateArticlescrap(Articlescrap articlescrap) throws Exception {
		TransportClient client=transportClient.getClient();
		 UpdateRequest updateRequest = new UpdateRequest(index, type,articlescrap.getId());
		 if(articlescrap.getUpdate_time()==null){
		     articlescrap.setUpdate_time(new Date());
		 }
		 updateRequest.doc(ArticlescrapEs.ConvertToEs(articlescrap).toString());
		 client.update(updateRequest).get();
		 return 1;
	}

	@Override
	public List<Articlescrap> getArticlescrapByIds(String[] ids) {
		TransportClient client=transportClient.getClient();
		SearchRequestBuilder responsebuilder = client.prepareSearch(index).setTypes(type).setQuery(QueryBuilders.idsQuery().addIds(ids));
		SearchResponse myresponse = responsebuilder.execute().actionGet();		
		SearchHits hits = myresponse.getHits();
		if(hits.getTotalHits()>0){
			List<Articlescrap> list = new ArrayList<Articlescrap>();
			for (int i = 0; i < hits.getHits().length; i++) {
				String source = hits.getHits()[i].getSourceAsString();
				ArticlescrapEs articlescrapEs = JSON.parseObject(source, ArticlescrapEs.class);
				Articlescrap articlescrap = ArticlescrapEs.ConvertToVo(articlescrapEs);
				articlescrap.setId(hits.getHits()[i].getId());
				list.add(articlescrap);
			}
			return list;
		}
		return null;
	}

	private  String getContent(String id) {
		TransportClient client=transportClient.getClient();
		SearchRequestBuilder responsebuilder = client.prepareSearch(index).setTypes(type);
		responsebuilder.setQuery(QueryBuilders.idsQuery().addIds(id));
		String[] fields= {"content"};
		responsebuilder.storedFields(fields);
		SearchResponse myresponse = responsebuilder.setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		if(hits.getTotalHits()>0){
		    Map<String,SearchHitField> map=hits.getHits()[0].getFields();
		    SearchHitField sf = map.get("content");
		    return sf==null?null:sf.getValue();
		}
		return null;
	}
	
	private BoolQueryBuilder getListQueryBuilder(ArticlescrapCondtion condition){
		BoolQueryBuilder boolBuilder = new BoolQueryBuilder();
		if(condition!=null){
			if(!StringUtils.isNullOrEmpty(condition.getTitle())){
				boolBuilder.must(QueryBuilders.matchQuery("title", condition.getTitle()));
			}
			if(!StringUtils.isNullOrEmpty(condition.getAuthor())){
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
			if(condition.getStartTimeFrom()!=null){
				boolBuilder.must(QueryBuilders.rangeQuery("start_time").gte(condition.getStartTimeFrom()));
			}
			if(condition.getStartTimeTo()!=null){
				boolBuilder.must(QueryBuilders.rangeQuery("start_time").lte(condition.getStartTimeTo()));
			}
			if(condition.getGreaterStartTime()!=null){
				boolBuilder.must(QueryBuilders.rangeQuery("start_time").gt(condition.getGreaterStartTime()));
			}
			if(condition.getLessThanStartTime()!=null){
				boolBuilder.must(QueryBuilders.rangeQuery("start_time").lt(condition.getLessThanStartTime()));
			}
			if(condition.getPicNum()!=null){
				boolBuilder.must(QueryBuilders.rangeQuery("pic_num").gte(condition.getPicNum()));
			}
			if(condition.getWithoutIds()!=null && condition.getWithoutIds().length>0){
				boolBuilder.mustNot(QueryBuilders.idsQuery().addIds(condition.getWithoutIds()));
			}
		    if(!StringUtils.isNullOrEmpty(condition.getKeyword())){
		    	String[] matchFields={"title","sub_content","keywords","content"};
		    	boolBuilder.must(QueryBuilders.multiMatchQuery(condition.getKeyword(),matchFields));
		    }
		}
		return boolBuilder;
	}
}
