package com.dgjs.service.content;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.persistence.condition.DraftCondition;

public interface DraftService {

    public int saveDraft(Draft draft);
	
	public Draft selectById(String id);
	
	public PageInfoDto<Draft> listDrafts(DraftCondition condition);
	
	public int updateDraft(Draft draft) throws Exception;
	
	public int deleteDraft(String id);
	
	public Draft selectByIdAll(String id);
	
}
