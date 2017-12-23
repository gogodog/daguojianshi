package com.dgjs.model.dto;

import java.util.List;

import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.persistence.DraftAPRecord;

public class DraftDto {

	private Draft draft;
	
	private List<DraftAPRecord> draftAPRecords;

	public Draft getDraft() {
		return draft;
	}

	public void setDraft(Draft draft) {
		this.draft = draft;
	}

	public List<DraftAPRecord> getDraftAPRecords() {
		return draftAPRecords;
	}

	public void setDraftAPRecords(List<DraftAPRecord> draftAPRecords) {
		this.draftAPRecords = draftAPRecords;
	}
	
	
}
