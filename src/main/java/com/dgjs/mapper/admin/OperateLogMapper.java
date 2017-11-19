package com.dgjs.mapper.admin;

import java.util.List;

import com.dgjs.model.persistence.OperateLog;
import com.dgjs.model.persistence.condition.OperateLogCondition;

public interface OperateLogMapper {

	public int save(OperateLog operateLog);
	
	public List<OperateLog> list(OperateLogCondition condition);
	
	public int count(OperateLogCondition condition);
	
	public OperateLog selectById(Long id);
	
}
