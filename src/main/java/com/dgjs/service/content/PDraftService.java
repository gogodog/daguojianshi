package com.dgjs.service.content;

import java.util.Date;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.PDraft;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.condition.PDraftCondition;

public interface PDraftService {

	public int saveDraft(PDraft draft);

    public PDraft selectById(String id);
    
    public PDraft selectByIdAll(String id);
    
    public String getContent(String id);
    
    public PageInfoDto<PDraft> listDrafts(PDraftCondition condition);
    
    public int updateDraft(PDraft draft) throws Exception;
    
    public int deleteDraft(String id);
    
    public int submitAudit(String id) throws Exception;
    
	public int audit(String id,Pending_Status status,Integer audit_user_id,String audit_desc) throws Exception;
	
	public int publish(String id,Integer publish_user_id,Long visits,Date show_time,boolean isShowNow) throws Exception;
	
}
