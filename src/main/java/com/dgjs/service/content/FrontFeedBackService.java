package com.dgjs.service.content;

import com.dgjs.model.dto.FeedBackDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.FrontFeedBack;
import com.dgjs.model.persistence.condition.FrontFeedBackCondition;

public interface FrontFeedBackService {

    public int save(FrontFeedBack feedBack);
	
	public int getLevelCount(String articlescrapId,Judge_Level judge_level);
	
	public PageInfoDto<FeedBackDto> listFeedBack(FrontFeedBackCondition condition);
}
