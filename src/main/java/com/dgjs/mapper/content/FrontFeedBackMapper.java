package com.dgjs.mapper.content;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.FrontFeedBack;
import com.dgjs.model.persistence.condition.FrontFeedBackCondition;

public interface FrontFeedBackMapper {
	
    public int save(FrontFeedBack feedBack);
	
	public int getLevelCount(@Param("articlescrap_id")String articlescrapId,@Param("judge_level")Judge_Level judge_level);

    public List<FrontFeedBack> listFeedBack(FrontFeedBackCondition condition);
    
    public int countFeedBack(FrontFeedBackCondition condition);
}
