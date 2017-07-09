package com.dgjs.mapper.content;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.FeedBack;
import com.dgjs.model.persistence.condition.FeedBackCondition;

public interface FeedBackMapper {

	public int save(FeedBack feedBack);
	
	public int getLevelCount(@Param("articlescrap_id")String articlescrapId,@Param("judge_level")Judge_Level judge_level);

    public List<FeedBack> listFeedBack(FeedBackCondition condition);
    
    public int countFeedBack(FeedBackCondition condition);
}
