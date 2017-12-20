package com.dgjs.service.content;

import java.util.List;


import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.DraftAPRecord;

public interface DraftAPRecordService {

	public List<DraftAPRecord> list(List<String> draftIds,List<Pending_Status> statusList,
			Integer operator);
}
