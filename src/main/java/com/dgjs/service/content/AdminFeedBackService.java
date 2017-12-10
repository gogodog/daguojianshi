package com.dgjs.service.content;

import com.dgjs.model.dto.AdminFeedBackDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminFeedBack;
import com.dgjs.model.persistence.condition.AdminFeedBackCondition;

public interface AdminFeedBackService {

	public int save(AdminFeedBack adminFeedBack);
	
	public PageInfoDto<AdminFeedBackDto> list(AdminFeedBackCondition condition);
	
	public AdminFeedBack selectById(Long id);
	
}