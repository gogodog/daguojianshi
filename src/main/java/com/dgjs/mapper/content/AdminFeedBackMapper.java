package com.dgjs.mapper.content;

import java.util.List;

import com.dgjs.model.persistence.AdminFeedBack;
import com.dgjs.model.persistence.condition.AdminFeedBackCondition;

public interface AdminFeedBackMapper {

	public int save(AdminFeedBack adminFeedBack);
	
	public List<AdminFeedBack> listFeedBack(AdminFeedBackCondition condition);
	
	public int countFeedBack(AdminFeedBackCondition condition);
	
	public AdminFeedBack selectById(Long id);
	
	public int updateToReply(Long id);
}
