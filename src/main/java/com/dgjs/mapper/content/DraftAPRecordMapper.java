package com.dgjs.mapper.content;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.DraftAPRecord;

public interface DraftAPRecordMapper {

	public int save(DraftAPRecord draftAPRecord);
	
	public List<DraftAPRecord> list(@Param("draft_ids")List<String> draftIds,@Param("status_list")List<Pending_Status> statusList,
			@Param("operator")Integer operator);
}
