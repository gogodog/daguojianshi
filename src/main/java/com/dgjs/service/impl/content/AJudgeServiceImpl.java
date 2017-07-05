package com.dgjs.service.impl.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.AJudgeMapper;
import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.AJudge;
import com.dgjs.service.content.AJudgeService;

@Service
public class AJudgeServiceImpl implements AJudgeService{

	@Autowired
	AJudgeMapper mapper;
	
	@Override
	public int save(AJudge aJudge) {
		return mapper.save(aJudge);
	}

	@Override
	public int getLevelCount(String articlescrapId, Judge_Level judge_level) {
		return mapper.getLevelCount(articlescrapId, judge_level);
	}

}
