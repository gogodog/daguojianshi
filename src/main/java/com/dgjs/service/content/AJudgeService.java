package com.dgjs.service.content;

import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.AJudge;

public interface AJudgeService {

	public int save(AJudge aJudge);
	
	public int getLevelCount(String articlescrapId,Judge_Level judge_level);
}
