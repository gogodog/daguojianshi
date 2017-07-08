package com.dgjs.service.content;

import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.FeedBack;

public interface FeedBackService {

	public int save(FeedBack feedBack);
	
	public int getLevelCount(String articlescrapId,Judge_Level judge_level);
}
