package com.dgjs.mapper.admin;

import java.util.List;


import com.dgjs.model.persistence.NoticeMessage;
import com.dgjs.model.persistence.condition.NoticeMessageCondition;

public interface NoticeMessageMapper {

	public int save(NoticeMessage noticeMessage);
	
	public List<NoticeMessage> getByAdminId(NoticeMessageCondition condition);
	
	public int getByAdminIdCount(NoticeMessageCondition condition);
	
	public int readMessage(List<Long> list);
}
