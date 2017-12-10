package com.dgjs.service.admin;

import java.util.List;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Message_Related_Type;
import com.dgjs.model.persistence.NoticeMessage;
import com.dgjs.model.persistence.condition.NoticeMessageCondition;

public interface NoticeMessageService {

	public int save(NoticeMessage noticeMessage);
	
	public PageInfoDto<NoticeMessage> list(NoticeMessageCondition condtion);
	
	public int readMessage(List<Long> ids);
	
	public int getUnReadCount(Integer adminId);
	
	public List<NoticeMessage> getByTypeRelatedId(Message_Related_Type type,Long relatedId);
}
