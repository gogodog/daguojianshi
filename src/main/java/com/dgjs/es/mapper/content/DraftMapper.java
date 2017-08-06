package com.dgjs.es.mapper.content;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.persistence.condition.DraftCondition;

public interface DraftMapper {

	public int saveDraft(Draft draft);
	
	public Draft selectById(String id);
	
	public PageInfoDto<Draft> listDrafts(DraftCondition condition);
	
	public int updateDraft(Draft draft) throws Exception;
	
	public int deleteDraft(String id);
	
	public String getContent(String id);
}
