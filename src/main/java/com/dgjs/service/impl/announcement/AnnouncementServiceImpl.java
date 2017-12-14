package com.dgjs.service.impl.announcement;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.announcement.AnnouncementMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Announcement;
import com.dgjs.model.persistence.condition.AnnouncementCondition;
import com.dgjs.service.announcement.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService{

	@Autowired
	AnnouncementMapper mapper;
	
	@Override
	public int save(Announcement announcement) {
		return mapper.save(announcement);
	}

	@Override
	public int updateStatus(Integer id, UpDown_Status status) {
		if(status == UpDown_Status.UP){
			Announcement announcement = mapper.selectById(id);
			if(announcement == null){
				return 0;
			}
			if(announcement.getShow_time()==null){
				return mapper.updateStatus(id, status,new Date());
			}
		}
		return mapper.updateStatus(id, status,null);
	}

	@Override
	public PageInfoDto<Announcement> list(AnnouncementCondition condition) {
		condition.setBeginNum((condition.getCurrentPage()-1)*condition.getOnePageSize());
		List<Announcement> list=mapper.list(condition);
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = mapper.count(condition);
		}
		return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, list);
	}

}
