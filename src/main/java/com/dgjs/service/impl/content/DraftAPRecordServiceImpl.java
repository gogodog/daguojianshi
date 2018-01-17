package com.dgjs.service.impl.content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.DraftAPRecordMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
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

	@Override
	public Map<String, String> getLastRejectMsg(PageInfoDto<Draft> pageinfo) {
		if(pageinfo!=null && !pageinfo.getObjects().isEmpty()){
			List<Draft> list = pageinfo.getObjects();
			List<String> draftIds = new ArrayList<String>();
			for(Draft draft:list){
				if(draft.getStatus()==Pending_Status.Audit_FAIL){
					draftIds.add(draft.getId());
				}
				if(draftIds.size()>0){
					Map<String,String> map = new HashMap<String,String>();
					List<DraftAPRecord> draftRecordList = draftAPRecordMapper.list(draftIds, Arrays.asList(Pending_Status.Audit_FAIL), null);
				    for(DraftAPRecord record:draftRecordList){
				    	if(map.containsKey(record.getDraft_id())){
				    		continue;
				    	}else{
				    		map.put(record.getDraft_id(), record.getOperate_desc());
				    	}
				    }
				    return map;
				}
			}
		}
		return null;
	}

}
