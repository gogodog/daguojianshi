package com.dgjs.service.announcement;


import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Announcement;
import com.dgjs.model.persistence.condition.AnnouncementCondition;

public interface AnnouncementService {

	public int save(Announcement announcement);
	
	public int updateStatus(Integer id,UpDown_Status status);
	
	public PageInfoDto<Announcement> list(AnnouncementCondition condition);
}
