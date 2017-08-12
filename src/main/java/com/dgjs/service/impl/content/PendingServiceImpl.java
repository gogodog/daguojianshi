package com.dgjs.service.impl.content;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.es.mapper.content.PendingMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.condition.PendingCondition;
import com.dgjs.service.content.PendingService;

@Service
public class PendingServiceImpl implements PendingService{
	
	@Autowired
	PendingMapper pendingMapper;

	@Override
	public int savePending(Pending pending) {
		return pendingMapper.savePending(pending);
	}

	@Override
	public int audit(String id, Pending_Status status, Integer audit_user_id,
			String audit_desc) throws Exception {
		return pendingMapper.audit(id, status, audit_user_id, audit_desc);
	}

	@Override
	public Pending selectById(String id) {
		return pendingMapper.selectById(id);
	}

	@Override
	public int publish(String id, Integer publish_user_id, Date publish_time,
			int visits, Date show_time) throws Exception {
		int flag = pendingMapper.publish(id, publish_user_id, publish_time, visits, show_time);
		return flag;
	}

	@Override
	public PageInfoDto<Pending> listPending(PendingCondition condition) {
		return pendingMapper.listPending(condition);
	}

}
