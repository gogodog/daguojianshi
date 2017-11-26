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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.es.client.ESTransportClient;
import com.dgjs.es.client.FastFDSClient;
import com.dgjs.es.mapper.content.PendingMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.Pic_Sync_Status;
import com.dgjs.model.es.PendingEs;
import com.dgjs.model.persistence.condition.PendingCondition;
import com.dgjs.utils.DateUtils;
import com.dgjs.utils.StringUtils;

@Service
public class PendingMapperImpl implements PendingMapper{
	
	private static final Logger logger = LoggerFactory.getLogger(PendingMapperImpl.class);
	
	@Autowired
	ESTransportClient transportClient;
	
	@Autowired
	FastFDSClient fastFDSClient;
	
	final static String index = "dp_v5";
	
	final static String type = "pending_v5";
	
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
		if(pendingEs==null||pendingEs.getStatus()!=Pending_Status.AUDIT_PENDING.getKey()){
			return 0;
		}
		Date now = new Date();
		String datenow = DateUtils.parseStringFromDate(now);
		pendingEs.setUpdate_time(datenow);
		pendingEs.setAudit_time(datenow);
		pendingEs.setStatus(status.getKey());
		pendingEs.setAudit_user_id(audit_user_id);
		if(status==Pending_Status.Audit_FAIL){
			pendingEs.setAudit_desc(audit_desc);
		}else{
			//此处建议用消息队列或者定时任务处理
			movePic(pendingEs);
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
		pendingEs.setId(id);
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
		if(pendingEs.getPic_sync_Status()!=Pic_Sync_Status.SYNCHRONIZED.getKey()){
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
				pendingEs.setId(hits.getHits()[i].getId());
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
		    List<Pic_Sync_Status> picSyncStatusList= condition.getPicSyncStatus();
		    if(picSyncStatusList!=null&&picSyncStatusList.size()>0){
		    	List<Integer> picSyncStatus = new ArrayList<Integer>();
		    	for(Pic_Sync_Status status : picSyncStatusList){
		    		 picSyncStatus.add(status.getKey());
		    	}
		    	boolBuilder.must(QueryBuilders.termsQuery("pic_sync_Status", picSyncStatus));
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
		if(pendingEs!=null){
			String content = getContent(id);
			pendingEs.setContent(content);
		}
		return pendingEs;
	}

	private void movePic(PendingEs pending){
		String[] pics = pending.getPictures();
		//如果没有图片，设置为同步完成
		if(pics==null||pics.length==0){
			pending.setPic_sync_Status(Pic_Sync_Status.SYNCHRONIZED.getKey());
			return;
		}
		String[] fastfdsPics = new String[pics.length];
		int progress=pending.getProgress();
		try {
			for(int i=0;i<pics.length;i++){
				String pic=pics[i];
				if(progress == i){
					String[] uploadFile = fastFDSClient.uploadFile(pic);//fastfds上传
					if(uploadFile==null||uploadFile.length!=2){
						break;
					}else{
						fastfdsPics[progress]=StringUtils.jointString("/",uploadFile[0],"/",uploadFile[1]);
						progress++;
					}
				}
			}
		 }
		 catch (Exception e) {
				logger.error("uploadFile to fastfds exception,param="+JSON.toJSONString(pending), e);
		 } 
		 if(progress > 0 && progress < pics.length){
			  pending.setProgress(progress);
			  pending.setPic_sync_Status(Pic_Sync_Status.SYNCHING.getKey());
	     }else if(progress == pics.length){
	    	  pending.setProgress(progress);
	    	  pending.setPic_sync_Status(Pic_Sync_Status.SYNCHRONIZED.getKey());
	     }
		 for(int i=0;i<progress;i++){
			 pics[i]=fastfdsPics[i];
		 }
		 pending.setPictures(pics);
	}

	@Override
	public int deletePending(String id) {
		TransportClient client=transportClient.getClient();
		DeleteResponse response=client.prepareDelete(index, type, id).execute().actionGet();
		return StringUtils.isNullOrEmpty(response.getId())?0:1;
	}

	@Override
	public int movePic(String aid) throws Exception{
		TransportClient client=transportClient.getClient();
		PendingEs pendingEs=selectWithContent(aid);
		movePic(pendingEs);
		String datenow = DateUtils.parseStringFromDate(new Date());
		pendingEs.setUpdate_time(datenow);
		UpdateRequest updateRequest = new UpdateRequest(index, type,aid);
		updateRequest.doc(pendingEs.toString());
		client.update(updateRequest).get();
		return 1;
	}
}
