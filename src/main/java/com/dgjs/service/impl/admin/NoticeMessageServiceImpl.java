package com.dgjs.service.impl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.NoticeMessageMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Read_Status;
import com.dgjs.model.persistence.NoticeMessage;
import com.dgjs.model.persistence.condition.NoticeMessageCondition;
import com.dgjs.service.admin.NoticeMessageService;

@Service
public class NoticeMessageServiceImpl implements NoticeMessageService{

	@Autowired
	NoticeMessageMapper mapper;
	
	@Override
	public int save(NoticeMessage noticeMessage) {
		return mapper.save(noticeMessage);
	}

	@Override
	public PageInfoDto<NoticeMessage> list(NoticeMessageCondition condition) {
		condition.setBeginNum((condition.getCurrentPage()-1)*condition.getOnePageSize());
		List<NoticeMessage> list=mapper.getByAdminId(condition);
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = mapper.getByAdminIdCount(condition);
		}
		return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, list);
	}

	@Override
	public int readMessage(List<Long> ids) {
		return mapper.readMessage(ids);
	}

	@Override
	public int getUnReadCount(Integer adminId) {
		NoticeMessageCondition condition = new NoticeMessageCondition();
		condition.setAdminId(adminId);
		condition.setStatus(Read_Status.UNREAD);
		return mapper.getByAdminIdCount(condition);
	}

}