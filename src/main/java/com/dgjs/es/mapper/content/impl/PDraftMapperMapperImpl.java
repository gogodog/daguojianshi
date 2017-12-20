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
import com.dgjs.es.mapper.content.PDraftMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.PDraft;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.es.PDraftEs;
import com.dgjs.model.persistence.condition.PDraftCondition;
import com.dgjs.utils.DateUtils;
import com.dgjs.utils.StringUtils;

@Service
public class PDraftMapperMapperImpl implements PDraftMapper{

	@Autowired
	ESTransportClient transportClient;
	
	final static String index = "dp_v2";
	
	final static String type = "draft_v2";
	
	@Override
	public int saveDraft(PDraft draft) {
		Date now = new Date();
		if(draft.getCreate_time()==null){
			draft.setCreate_time(now);
		}
		if(draft.getUpdate_time()==null){
			draft.setUpdate_time(now);
		}
	    return saveDraft2(PDraftEs.ConvertToEs(draft));
	}

	@Override
	public PDraft selectById(String id) {
		PDraftEs draftEs =selectById2(id);
		PDraft draft = PDraftEs.ConvertToVo(draftEs);
		return draft;
	}

	@Override
	public PDraft selectByIdAll(String id) {
		PDraftEs draftEs = selectByIdAll2(id);
		return PDraftEs.ConvertToVo(draftEs);
	}
	
	@Override
	public PageInfoDto<PDraft> listDrafts(PDraftCondition condition) {
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
		responsebuilder = responsebuilder.setFrom(condition.getBeginNum()).setSize(condition.getOnePageSize()).setExplain(true);
		if((condition.getIncludes()!=null && condition.getIncludes().length>0)||
				(condition.getExcludes()!=null&&condition.getExcludes().length>0)){
			responsebuilder.setFetchSource(condition.getIncludes(), condition.getExcludes());
		}
		SearchResponse myresponse = responsebuilder.execute().actionGet();
		SearchHits hits = myresponse.getHits();
		if(hits.getTotalHits()>0){
			List<PDraft> list = new ArrayList<PDraft>();
			for (int i = 0; i < hits.getHits().length; i++) {
				String source = hits.getHits()[i].getSourceAsString();
				PDraftEs draftEs = JSON.parseObject(source, PDraftEs.class);
				draftEs.setId(hits.getHits()[i].getId());
				PDraft draft = PDraftEs.ConvertToVo(draftEs);
				list.add(draft);
			}
		    return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), hits.getTotalHits(), list);
		}
		return null;
	}
	
	@Override
	public int updateDraft(PDraft draft) throws Exception {
		 if(draft.getUpdate_time()==null){
			 draft.setUpdate_time(new Date());
		 }
		 return updateDraft2(PDraftEs.ConvertToEs(draft));
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

	@Override
	public int audit(String id, Pending_Status status, Integer audit_user_id) throws Exception {
		PDraftEs draftEs = selectByIdAll2(id);
		if(draftEs==null||draftEs.getStatus()!=Pending_Status.AUDIT_PENDING.getKey()){
			return 0;
		}
		Date now = new Date();
		String nowStr = DateUtils.parseStringFromDate(now);
		draftEs.setUpdate_time(nowStr);
		draftEs.setAudit_time(nowStr);
		draftEs.setStatus(status.getKey());
		draftEs.setAudit_user_id(audit_user_id);
		draftEs.setHaveAudit(true);
	    return updateDraft2(draftEs);
	}

	@Override
	public PDraft publish(String id, Integer publish_user_id, Date publish_time)
			throws Exception {
		PDraftEs draftEs=selectByIdAll2(id);
		if(draftEs.getStatus()!=Pending_Status.PUBLISH_PENDING.getKey()){
			return null;
		}
		Date now = new Date();
		String nowStr = DateUtils.parseStringFromDate(now);
		draftEs.setUpdate_time(nowStr);
		draftEs.setPublish_time(nowStr);
		draftEs.setStatus(Pending_Status.PUBLISHED.getKey());
		draftEs.setPublish_user_id(publish_user_id);
		draftEs.setHavePublish(true);
		PDraft draft = PDraftEs.ConvertToVo(draftEs);
	    updateDraft(draft);
		return draft;
	}

	@Override
	public int submitAudit(String id) throws Exception {
		PDraftEs draftEs = selectByIdAll2(id);
		draftEs.setStatus(Pending_Status.AUDIT_PENDING.getKey());
		String datenow = DateUtils.parseStringFromDate(new Date());
		draftEs.setUpdate_time(datenow);
		return updateDraft2(draftEs);
	}
	
	private BoolQueryBuilder getListQueryBuilder(PDraftCondition condition){
		BoolQueryBuilder boolBuilder = new BoolQueryBuilder();
		if(condition!=null){
			if(condition.getUserId()!=null){
				boolBuilder.must(QueryBuilders.termQuery("user_id", condition.getUserId()));
			}
			if(condition.getStatus()!=null){
				boolBuilder.must(QueryBuilders.termQuery("status", condition.getStatus().getKey()));
			}
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
	
	@SuppressWarnings("deprecation")
	private int saveDraft2(PDraftEs draftEs){
		TransportClient client=transportClient.getClient();
		IndexRequestBuilder indexRequestBuilder = client.prepareIndex(index, type);
		IndexResponse response = indexRequestBuilder.setSource(draftEs.toString()).execute().actionGet();
	    return StringUtils.isNullOrEmpty(response.getId())?0:1;
	}

	private PDraftEs selectById2(String id){
		TransportClient client=transportClient.getClient();
		GetResponse response = client.prepareGet(index, type, id).get();
		PDraftEs draftEs = JSON.parseObject(response.getSourceAsString(), PDraftEs.class);
		draftEs.setId(id);
		return draftEs;
	}
	
	private PDraftEs selectByIdAll2(String id){
		PDraftEs draftEs = selectById2(id);
		if(draftEs!=null){
			draftEs.setContent(getContent(id));
		}
		return draftEs;
	}
	
	@SuppressWarnings("deprecation")
	private int updateDraft2(PDraftEs draftEs)  throws Exception {
		 TransportClient client=transportClient.getClient();
		 UpdateRequest updateRequest = new UpdateRequest(index,type,draftEs.getId());
		 updateRequest.doc(draftEs.toString());
		 client.update(updateRequest).get();
		 return 1;
	}

}
