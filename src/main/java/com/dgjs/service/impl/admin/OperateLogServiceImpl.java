package com.dgjs.service.impl.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.OperateLogMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.OperateLog;
import com.dgjs.model.persistence.condition.OperateLogCondition;
import com.dgjs.service.admin.OperateLogService;

@Service
public class OperateLogServiceImpl implements OperateLogService{

	@Autowired
	OperateLogMapper operateLogMapper;
	
	@Override
	public int save(OperateLog operateLog) {
		return operateLogMapper.save(operateLog);
	}

	@Override
	public PageInfoDto<OperateLog> list(OperateLogCondition condition) {
		condition.setBeginNum((condition.getCurrentPage()-1)*condition.getOnePageSize());
		List<OperateLog> list=operateLogMapper.list(condition);
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = operateLogMapper.count(condition);
		}
		return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, list);
	}

}
