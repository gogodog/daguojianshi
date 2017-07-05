package com.dgjs.mapper.content;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.AJudge;

public interface AJudgeMapper {

	public int save(AJudge aJudge);
	
	public int getLevelCount(@Param("articlescrap_id")String articlescrapId,@Param("judge_level")Judge_Level judge_level);
}
