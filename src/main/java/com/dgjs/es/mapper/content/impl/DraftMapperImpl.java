package com.dgjs.es.mapper.content.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.es.client.ESTransportClient;
import com.dgjs.es.mapper.content.DraftMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.es.DraftEs;
import com.dgjs.model.persistence.condition.DraftCondition;
import com.dgjs.utils.DateUtils;
import com.dgjs.utils.StringUtils;

@Service("draftMapper")
public class DraftMapperImpl implements DraftMapper{

	@Autowired
	ESTransportClient transportClient;
	
	final static String index = "dp_v5";
	
	final static String type = "draft_v5";
	
	@SuppressWarnings("deprecation")
	@Override
	public int saveDraft(Draft draft) {
		TransportClient client=transportClient.getClient();
		IndexRequestBuilder indexRequestBuilder =client.prepareIndex(index, type);
		Date now = new Date();
		if(draft.getCreate_time()==null){
			draft.setCreate_time(now);
		}
		if(draft.getUpdate_time()==null){
			draft.setUpdate_time(now);
		}
	    IndexResponse response = indexRequestBuilder.setSource(DraftEs.ConvertToEs(draft).toString()).execute().actionGet();
	    return StringUtils.isNullOrEmpty(response.getId())?0:1;
	}

	@Override
	public Draft selectById(String id) {
		TransportClient client=transportClient.getClient();
		GetResponse response = client.prepareGet(index, type, id).get();
		DraftEs draftEs = JSON.parseObject(response.getSourceAsString(), DraftEs.class);
		draftEs.setId(id);
		Draft draft = DraftEs.ConvertToVo(draftEs);
		return draft;
	}

	@Override
	public PageInfoDto<Draft> listDrafts(DraftCondition condition) {
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
			List<Draft> list = new ArrayList<Draft>();
			for (int i = 0; i < hits.getHits().length; i++) {
				String source = hits.getHits()[i].getSourceAsString();
				DraftEs draftEs = JSON.parseObject(source, DraftEs.class);
				draftEs.setId(hits.getHits()[i].getId());
				Draft draft = DraftEs.ConvertToVo(draftEs);
				list.add(draft);
			}
		    return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), hits.getTotalHits(), list);
		}
		return null;
	}
	
	private BoolQueryBuilder getListQueryBuilder(DraftCondition condition){
		BoolQueryBuilder boolBuilder = new BoolQueryBuilder();
		if(condition!=null){
			if(!StringUtils.isNullOrEmpty(condition.getTitle())){
				boolBuilder.must(QueryBuilders.matchQuery("title", condition.getTitle()));
			}
			if(!StringUtils.isNullOrEmpty(condition.getAuthor())){
				boolBuilder.must(QueryBuilders.termQuery("author", condition.getAuthor()));
			}
			if(condition.getType()!=null){
				boolBuilder.must(QueryBuilders.termQuery("type", condition.getType().getKey()));
			}
			if(condition.getCreateTimeFrom()!=null){
				boolBuilder.must(QueryBuilders.rangeQuery("create_time").gte(DateUtils.parseStringFromDate(condition.getCreateTimeFrom())));
			}
			if(condition.getCreateTimeTo()!=null){
				boolBuilder.must(QueryBuilders.rangeQuery("create_time").lte(DateUtils.parseStringFromDate(condition.getCreateTimeTo())));
			}
		    if(!StringUtils.isNullOrEmpty(condition.getKeyword())){
		    	String[] matchFields={"title","sub_content","keywords","content"};
		    	boolBuilder.must(QueryBuilders.multiMatchQuery(condition.getKeyword(),matchFields));
		    }
		}
		return boolBuilder;
	}

	@Override
	public int updateDraft(Draft draft) throws Exception{
		 TransportClient client=transportClient.getClient();
		 UpdateRequest updateRequest = new UpdateRequest(index, type,draft.getId());
		 if(draft.getUpdate_time()==null){
			 draft.setUpdate_time(new Date());
		 }
		 updateRequest.doc(DraftEs.ConvertToEs(draft).toString());
		 client.update(updateRequest).get();
		 return 0;
	}

	@Override
	public int deleteDraft(String id) {
		TransportClient client=transportClient.getClient();
		DeleteResponse response=client.prepareDelete(index, type, id).execute().actionGet();
		return StringUtils.isNullOrEmpty(response.getId())?0:1;
	}

	@Override
	public String getContent(String id) {
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
		    String content = sf.getValue();
		    return content;
		}
		return null;
	}

}
