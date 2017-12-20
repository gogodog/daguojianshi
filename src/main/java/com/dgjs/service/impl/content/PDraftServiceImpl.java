package com.dgjs.service.impl.content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.es.mapper.content.PDraftMapper;
import com.dgjs.mapper.content.DraftAPRecordMapper;
import com.dgjs.model.dto.DraftDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.dto.business.PDraft;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.DraftAPRecord;
import com.dgjs.model.persistence.condition.PDraftCondition;
import com.dgjs.service.content.PDraftService;
import com.dgjs.utils.PictureUtils;

@Service
public class PDraftServiceImpl implements PDraftService{

	@Autowired
	PDraftMapper draftMapper;
	
	@Value("${imageContextPath}${webBasePath}")
	private String webContextPath;
	
	@Autowired
	DraftAPRecordMapper draftAPRecordMapper;
	
	@Autowired
	ArticlescrapMapper articlescrapMapper;
	
	@Override
	public int saveDraft(PDraft draft) {
		return draftMapper.saveDraft(draft);
	}

	@Override
	public PDraft selectById(String id) {
		return draftMapper.selectById(id);
	}

	@Override
	public PageInfoDto<PDraft> listDrafts(PDraftCondition condition) {
		return draftMapper.listDrafts(condition);
	}

	@Override
	public int updateDraft(PDraft draft) throws Exception {
		return draftMapper.updateDraft(draft);
	}

	@Override
	public int deleteDraft(String id) {
		return draftMapper.deleteDraft(id);
	}

	@Override
	public PDraft selectByIdAll(String id) {
		PDraft draft = draftMapper.selectByIdAll(id);
		if(draft!=null){
		   draft.setContent(PictureUtils.render(draft.getPictures(),draft.getContent(),webContextPath));
	    }
		return draft;
	}

	@Override
	public int audit(String id, Pending_Status status, Integer audit_user_id, String audit_desc) throws Exception {
		int flag = draftMapper.audit(id, status, audit_user_id);
		if(flag>0){
			//保存审核记录
			DraftAPRecord draftAPRecord = new DraftAPRecord();
			draftAPRecord.setAction(status);
			draftAPRecord.setDraft_id(id);
			draftAPRecord.setOperate_desc(StringUtils.isEmpty(audit_desc)?null:audit_desc);
			draftAPRecord.setOperator(audit_user_id);
			flag = draftAPRecordMapper.save(draftAPRecord);
		}
		return flag;
	}

	@Override
	public int publish(String id, Integer publish_user_id, Long visits, Date showTime, boolean isShowNow)
			throws Exception {
		int flag = 0;
		Date now = new Date();
		PDraft draft = draftMapper.publish(id, publish_user_id, now);
		if(draft!=null){
			Articlescrap articlescrap = PDraft.transToArticlescrap(draft, visits, showTime);
			//如果需要立刻展示
			if(isShowNow){
				articlescrap.setStatus(Articlescrap_Status.UP);
				articlescrap.setShow_time(new Date());
			}
			flag=articlescrapMapper.saveArticlescrap(articlescrap);
			//保存发布记录
			DraftAPRecord draftAPRecord = new DraftAPRecord();
			draftAPRecord.setAction(Pending_Status.PUBLISHED);
			draftAPRecord.setDraft_id(id);
			draftAPRecord.setOperator(publish_user_id);
			flag = draftAPRecordMapper.save(draftAPRecord);
		}
		return flag;
	}

	@Override
	public String getContent(String id) {
		return draftMapper.getContent(id);
	}

	@Override
	public int submitAudit(String id)  throws Exception{
		return draftMapper.submitAudit(id);
	}

	@Override
	public PageInfoDto<DraftDto> listDraftsWithAPRecord(PDraftCondition condition) {
		PageInfoDto<PDraft> pageinfo = listDrafts(condition);
		if(pageinfo!=null && pageinfo.getObjects().size()>0){
			List<PDraft> list = pageinfo.getObjects();
			List<String> draftIds = new ArrayList<String>();
			List<DraftDto> resultList = new ArrayList<DraftDto>();
			for(PDraft draft:list){
				if(draft.isHaveAudit()||draft.isHavePublish()){
					//找出有发布审核记录的草稿id
					draftIds.add(draft.getId());
				}
			}
			List<DraftAPRecord> draftAPRecords = draftAPRecordMapper.list(draftIds, Arrays.asList(Pending_Status.Audit_FAIL,Pending_Status.PUBLISH_PENDING,Pending_Status.PUBLISHED), null);
		    Map<String,List<DraftAPRecord>> map = new HashMap<String,List<DraftAPRecord>>();
			for(DraftAPRecord draftAPRecord:draftAPRecords){
		    	if(map.containsKey(draftAPRecord.getDraft_id())){
		    		List<DraftAPRecord> draftAPRecordList = map.get(draftAPRecord.getDraft_id());
		    		draftAPRecordList.add(draftAPRecord);
		    	}else{
		    		List<DraftAPRecord> draftAPRecordList = new ArrayList<DraftAPRecord>();
		    		draftAPRecordList.add(draftAPRecord);
		    		map.put(draftAPRecord.getDraft_id(), draftAPRecordList);
		    	}
		    }
			for(PDraft draft:list){
				DraftDto dto = new DraftDto();
				dto.setDraft(draft);
				dto.setDraftAPRecords(map.get(draft.getId()));
				resultList.add(dto);
			}
			return PageInfoDto.getPageInfo(pageinfo.getCurrentPage(), pageinfo.getOnePageSize(), pageinfo.getTotalResults(), resultList);
		}
		return null;
	}

}
