package com.dgjs.service.impl.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.AdminFeedBackMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminFeedBack;
import com.dgjs.model.persistence.condition.AdminFeedBackCondition;
import com.dgjs.service.content.AdminFeedBackService;

@Service
public class AdminFeedBackServiceImpl implements AdminFeedBackService{

	@Autowired
	AdminFeedBackMapper adminFeedBackMapper;
	
	@Override
	public int save(AdminFeedBack adminFeedBack) {
		return adminFeedBackMapper.save(adminFeedBack);
	}

	@Override
	public PageInfoDto<AdminFeedBack> list(AdminFeedBackCondition condition) {
		int beginNum = (condition.getCurrentPage() - 1) * condition.getOnePageSize();
		condition.setBeginNum(beginNum);
		List<AdminFeedBack> list=adminFeedBackMapper.listFeedBack(condition);
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = adminFeedBackMapper.countFeedBack(condition);
		}
		return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, list);
	}

}
