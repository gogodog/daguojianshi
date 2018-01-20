package com.dgjs.service.content;

import java.util.Date;

import com.dgjs.model.dto.DraftDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.condition.DraftCondition;

public interface DraftService {

	public int saveDraft(Draft draft);

    public Draft selectById(String id);
    
    public Draft selectByIdAll(String id);
    
    public String getContent(String id);
    
    public PageInfoDto<Draft> listDrafts(DraftCondition condition);
    
    public int updateDraft(Draft draft) throws Exception;
    
    public int deleteDraft(String id);
    
    public int submitAudit(String id,String showPic) throws Exception;
    
	public int audit(String id,Pending_Status status,Integer audit_user_id,String audit_desc) throws Exception;
	
	public int publish(String id,Integer publish_user_id,Long visits,Date show_time,boolean isShowNow) throws Exception;
	
	public PageInfoDto<DraftDto> listDraftsWithAPRecord(DraftCondition condition);
}
