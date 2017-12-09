package com.dgjs.service.content;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminFeedBack;
import com.dgjs.model.persistence.condition.AdminFeedBackCondition;

public interface AdminFeedBackService {

	public int save(AdminFeedBack adminFeedBack);
	
	public PageInfoDto<AdminFeedBack> list(AdminFeedBackCondition condition);
	
}
