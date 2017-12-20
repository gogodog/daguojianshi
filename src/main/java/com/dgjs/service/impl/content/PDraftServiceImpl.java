package com.dgjs.service.impl.content;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.es.mapper.content.PDraftMapper;
import com.dgjs.mapper.content.DraftAPRecordMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.dto.business.PDraft;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.DraftAPRecord;
import com.dgjs.model.persistence.condition.PDraftCondition;
import com.dgjs.service.content.PDraftService;

@Service
public class PDraftServiceImpl implements PDraftService{

	@Autowired
	PDraftMapper draftMapper;
	
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
		return draftMapper.selectByIdAll(id);
	}

	@Override
	public int audit(String id, Pending_Status status, Integer audit_user_id, String audit_desc) throws Exception {
		int flag = draftMapper.audit(id, status, audit_user_id);
		if(flag>1){
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
		}
		return flag;
	}

	@Override
	public int movePic(String aid) throws Exception {
		return draftMapper.movePic(aid);
	}

	@Override
	public String getContent(String id) {
		return draftMapper.getContent(id);
	}

	@Override
	public int submitAudit(String id)  throws Exception{
		return draftMapper.submitAudit(id);
	}

}
