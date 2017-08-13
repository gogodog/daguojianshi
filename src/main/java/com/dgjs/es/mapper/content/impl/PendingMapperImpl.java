package com.dgjs.es.mapper.content.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.dgjs.es.mapper.content.PendingMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.es.PendingEs;
import com.dgjs.model.persistence.condition.PendingCondition;
import com.dgjs.utils.DateUtils;
import com.dgjs.utils.StringUtils;

@Service
public class PendingMapperImpl implements PendingMapper{

	@Autowired
	ESTransportClient transportClient;
	
	final static String index = "dgjs_v4";
	
	final static String type = "pending_v4";
	
	@SuppressWarnings("deprecation")
	@Override
	public int savePending(Pending pending) {
		TransportClient client=transportClient.getClient();
		IndexRequestBuilder indexRequestBuilder =client.prepareIndex(index, type);
	    IndexResponse response = indexRequestBuilder.setSource(PendingEs.ConvertToEs(pending).toString()).execute().actionGet();
	    return StringUtils.isNullOrEmpty(response.getId())?0:1;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int audit(String id,Pending_Status status, Integer audit_user_id,
			String audit_desc) throws Exception{
		TransportClient client=transportClient.getClient();
		PendingEs pendingEs=selectWithContent(id);
		Date now = new Date();
		String datenow = DateUtils.parseStringFromDate(now);
		if(pendingEs.getStatus()!=Pending_Status.AUDIT_PENDING.getKey()){
			return 0;
		}
		pendingEs.setUpdate_time(datenow);
		pendingEs.setAudit_time(datenow);
		pendingEs.setStatus(status.getKey());
		pendingEs.setAudit_user_id(audit_user_id);
		if(status==Pending_Status.Audit_FAIL){
			pendingEs.setAudit_desc(audit_desc);
		}
		UpdateRequest updateRequest = new UpdateRequest(index, type,id);
		updateRequest.doc(pendingEs.toString());
		client.update(updateRequest).get();
		return 1;
	}

	@Override
	public Pending selectById(String id) {
		TransportClient client=transportClient.getClient();
		GetResponse response = client.prepareGet(index, type, id).get();
		PendingEs pendingEs = JSON.parseObject(response.getSourceAsString(), PendingEs.class);
		Pending pending = PendingEs.ConvertToVo(pendingEs);
		return pending;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Pending publish(String id,Integer publish_user_id, Date publish_time, int visits,
			Date show_time) throws Exception{
		TransportClient client=transportClient.getClient();
		PendingEs pendingEs=selectWithContent(id);
		if(pendingEs.getStatus()!=Pending_Status.PUBLISH_PENDING.getKey()){
			return null;
		}
		Date now = new Date();
		String datenow = DateUtils.parseStringFromDate(now);
		pendingEs.setUpdate_time(datenow);
		pendingEs.setPublish_time(datenow);
		pendingEs.setStatus(Pending_Status.PUBLISHED.getKey());
		pendingEs.setPublish_user_id(publish_user_id);
		pendingEs.setShow_time(DateUtils.parseStringFromDate(show_time));
		pendingEs.setVisits(visits);
		UpdateRequest updateRequest = new UpdateRequest(index, type,id);
		updateRequest.doc(pendingEs.toString());
		client.update(updateRequest).get();
		Pending pending=selectById(id);
		pending.setContent(getContent(id));
		return pending;
	}

	@Override
	public PageInfoDto<Pending> listPending(PendingCondition condition) {
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
			List<Pending> list = new ArrayList<Pending>();
			for (int i = 0; i < hits.getHits().length; i++) {
				String source = hits.getHits()[i].getSourceAsString();
				PendingEs pendingEs = JSON.parseObject(source, PendingEs.class);
				Pending pending = PendingEs.ConvertToVo(pendingEs);
				list.add(pending);
			}
		    return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), hits.getTotalHits(), list);
		}
		return null;
	}
	
	private BoolQueryBuilder getListQueryBuilder(PendingCondition condition){
		BoolQueryBuilder boolBuilder = new BoolQueryBuilder();
		if(condition!=null){
			if(!StringUtils.isNullOrEmpty(condition.getTitle())){
				boolBuilder.must(QueryBuilders.matchQuery("title", condition.getTitle()));
			}
			if(condition.getType()!=null){
				boolBuilder.must(QueryBuilders.termQuery("type", condition.getType().getKey()));
			}
			if(condition.getStatus()!=null){
				boolBuilder.must(QueryBuilders.termQuery("status", condition.getStatus().getKey()));
			}
			if(condition.getUserId()!=null){
				boolBuilder.must(QueryBuilders.termQuery("user_id",condition.getUserId()));
			}
		    if(!StringUtils.isNullOrEmpty(condition.getKeyword())){
		    	String[] matchFields={"title","sub_content","keywords","content"};
		    	boolBuilder.must(QueryBuilders.multiMatchQuery(condition.getKeyword(),matchFields));
		    }
		}
		return boolBuilder;
	}

	@Override
	public Pending selectByIdAll(String id) {
		PendingEs pendingEs = selectWithContent(id);
		Pending pending = PendingEs.ConvertToVo(pendingEs);
		return pending;
	}
	
	private String getContent(String id) {
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
	
	private PendingEs selectWithContent(String id){
		TransportClient client=transportClient.getClient();
		GetResponse response = client.prepareGet(index, type, id).get();
		PendingEs pendingEs = JSON.parseObject(response.getSourceAsString(), PendingEs.class);
		String content = getContent(id);
		pendingEs.setContent(content);
		return pendingEs;
	}

}
