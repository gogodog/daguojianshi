package com.dgjs.mapper.content;

import java.util.List;

import com.dgjs.model.persistence.DraftAPRecord;

public interface DraftAPRecordMapper {

	public int save(DraftAPRecord draftAPRecord);
	
	public List<DraftAPRecord> list(DraftAPRecord draftAPRecord);
}
