package com.dgjs.service.impl.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.AdminUserMapper;
import com.dgjs.mapper.content.AdminFeedBackMapper;
import com.dgjs.model.dto.AdminFeedBackDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.AdminFeedBack;
import com.dgjs.model.persistence.condition.AdminFeedBackCondition;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.result.AdminUserResult;
import com.dgjs.service.content.AdminFeedBackService;

@Service
public class AdminFeedBackServiceImpl implements AdminFeedBackService{

	@Autowired
	AdminFeedBackMapper adminFeedBackMapper;
	
	@Autowired
	AdminUserMapper adminUserMapper;
	
	@Override
	public int save(AdminFeedBack adminFeedBack) {
		return adminFeedBackMapper.save(adminFeedBack);
	}

	@Override
	public PageInfoDto<AdminFeedBackDto> list(AdminFeedBackCondition condition) {
		int beginNum = (condition.getCurrentPage() - 1) * condition.getOnePageSize();
		condition.setBeginNum(beginNum);
		List<AdminFeedBack> list=adminFeedBackMapper.listFeedBack(condition);
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = adminFeedBackMapper.countFeedBack(condition);
		}
		List<AdminFeedBackDto> resultList = null;
		if(list!=null && !list.isEmpty()){
			resultList = new ArrayList<AdminFeedBackDto>();
			List<Integer> adminIds = new ArrayList<Integer>();
			for(AdminFeedBack adminFeedBack:list){
				adminIds.add(adminFeedBack.getUserId());
			}
			AdminUserCondition adminUserCondition = new AdminUserCondition();
			adminUserCondition.setAdminIds(adminIds);
			List<AdminUserResult> adminUsers=adminUserMapper.list(adminUserCondition);
			Map<Integer,AdminUserResult> map = AdminUserResult.fromListToMap(adminUsers);
			for(AdminFeedBack adminFeedBack:list){
				AdminFeedBackDto dto = new AdminFeedBackDto();
				dto.setAdminUser(map.get(adminFeedBack.getUserId()));
				dto.setFeedBack(adminFeedBack);
				resultList.add(dto);
			}
		}
		return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, resultList);
	}

	@Override
	public AdminFeedBack selectById(Long id) {
		return adminFeedBackMapper.selectById(id);
	}

}
