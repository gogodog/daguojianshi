package com.dgjs.mapper.announcement;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Announcement;
import com.dgjs.model.persistence.condition.AnnouncementCondition;

public interface AnnouncementMapper {

	public int save(Announcement announcement);
	
	public List<Announcement> list(AnnouncementCondition condition);
	
	public int updateStatus(@Param("id")Integer id,@Param("status") UpDown_Status status,@Param("show_time")Date showTime);
	
	public int count(AnnouncementCondition condition);
	
	public Announcement selectById(Integer id);
}
