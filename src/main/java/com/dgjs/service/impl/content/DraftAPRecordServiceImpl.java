package com.dgjs.service.impl.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.DraftAPRecordMapper;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.DraftAPRecord;
import com.dgjs.service.content.DraftAPRecordService;

@Service
public class DraftAPRecordServiceImpl implements DraftAPRecordService{

	@Autowired
	DraftAPRecordMapper draftAPRecordMapper;
	
	@Override
	public List<DraftAPRecord> list(List<String> draftIds, List<Pending_Status> statusList, Integer operator) {
		return draftAPRecordMapper.list(draftIds, statusList, operator);
	}

}
