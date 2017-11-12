package com.dgjs.service.admin;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.OperateLog;
import com.dgjs.model.persistence.condition.OperateLogCondition;

public interface OperateLogService {

	public int save(OperateLog operateLog);
	
	public PageInfoDto<OperateLog> list(OperateLogCondition condition);
}
