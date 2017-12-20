package com.dgjs.model.dto;

import java.util.List;

import com.dgjs.model.dto.business.PDraft;
import com.dgjs.model.persistence.DraftAPRecord;

public class DraftDto {

	private PDraft draft;
	
	private List<DraftAPRecord> draftAPRecords;

	public PDraft getDraft() {
		return draft;
	}

	public void setDraft(PDraft draft) {
		this.draft = draft;
	}

	public List<DraftAPRecord> getDraftAPRecords() {
		return draftAPRecords;
	}

	public void setDraftAPRecords(List<DraftAPRecord> draftAPRecords) {
		this.draftAPRecords = draftAPRecords;
	}
	
	
}
