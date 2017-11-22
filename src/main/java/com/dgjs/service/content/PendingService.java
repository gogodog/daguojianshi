package com.dgjs.service.content;

import java.util.Date;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.condition.PendingCondition;

public interface PendingService {

    public int savePending(String id)  throws Exception ;
	
	public int audit(String id,Pending_Status status,Integer audit_user_id,String audit_desc) throws Exception;
	
	public Pending selectById(String id);
	
	public int publish(String id,Integer publish_user_id,int visits,Date show_time,boolean isShowNow) throws Exception;
	
	public PageInfoDto<Pending> listPending(PendingCondition condition);
	
	public Pending selectByIdAll(String id);
	
	public int movePic(String aid) throws Exception;
}
