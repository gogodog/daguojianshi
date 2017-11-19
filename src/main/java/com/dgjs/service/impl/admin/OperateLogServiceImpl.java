package com.dgjs.service.impl.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.admin.AdminUserMapper;
import com.dgjs.mapper.admin.OperateLogMapper;
import com.dgjs.model.dto.OperateLogDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.OperateLog;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.condition.OperateLogCondition;
import com.dgjs.model.persistence.result.AdminUserResult;
import com.dgjs.service.admin.OperateLogService;
import com.mysql.jdbc.StringUtils;

@Service
public class OperateLogServiceImpl implements OperateLogService{

	@Autowired
	OperateLogMapper operateLogMapper;
	
	@Autowired
	AdminUserMapper adminUserMapper;
	
	
	@Override
	public int save(OperateLog operateLog) {
		return operateLogMapper.save(operateLog);
	}

	@Override
	public PageInfoDto<OperateLogDto> list(OperateLogCondition condition) {
		if(!StringUtils.isNullOrEmpty(condition.getRealname())||!StringUtils.isNullOrEmpty(condition.getUsername())){
			AdminUserCondition auc = new AdminUserCondition();
			auc.setUsername(condition.getUsername());
			auc.setReal_name(condition.getRealname());
			List<Integer> adminIdList = adminUserMapper.getListIds(auc);
			if(adminIdList.isEmpty()){
				return null;
			}else{
				condition.setAdminIds(adminIdList);
			}
		}
		condition.setBeginNum((condition.getCurrentPage()-1)*condition.getOnePageSize());
		List<OperateLog> list=operateLogMapper.list(condition);
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = operateLogMapper.count(condition);
		}
		if(list!=null&&!list.isEmpty()){
			List<OperateLogDto> dtoList = new ArrayList<OperateLogDto>(list.size());
			List<Integer> adminIds = new ArrayList<Integer>();
			for(OperateLog operateLog:list){
				adminIds.add(operateLog.getAdmin_id());
			}
			AdminUserCondition auCondition = new AdminUserCondition();
			auCondition.setAdminIds(adminIds);;
			List<AdminUserResult> auList= adminUserMapper.list(auCondition);
			Map<Integer,AdminUserResult> map = new HashMap<Integer,AdminUserResult>();
			for(AdminUserResult au:auList){
				map.put(au.getId(), au);
			}
			for(OperateLog operateLog:list){
				OperateLogDto dto = new OperateLogDto();
				dto.setOperateLog(operateLog);
				dto.setRealname(map.get(operateLog.getAdmin_id()).getReal_name());
				dto.setUsername(map.get(operateLog.getAdmin_id()).getUsername());
				dtoList.add(dto);
			}
			return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, dtoList);
		}
		return null;
	}

	@Override
	public OperateLog selectById(Long id) {
		return operateLogMapper.selectById(id);
	}

}
