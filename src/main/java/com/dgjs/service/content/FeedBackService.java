package com.dgjs.service.content;

import com.dgjs.model.dto.FeedBackDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.FeedBack;
import com.dgjs.model.persistence.condition.FeedBackCondition;

public interface FeedBackService {

	public int save(FeedBack feedBack);
	
	public int getLevelCount(String articlescrapId,Judge_Level judge_level);
	
	public PageInfoDto<FeedBackDto> listFeedBack(FeedBackCondition condition);
}
