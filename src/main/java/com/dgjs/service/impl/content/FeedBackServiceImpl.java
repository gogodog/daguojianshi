package com.dgjs.service.impl.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.FeedBackMapper;
import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.FeedBack;
import com.dgjs.service.content.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService{

	@Autowired
	FeedBackMapper mapper;
	
	@Override
	public int save(FeedBack aJudge) {
		return mapper.save(aJudge);
	}

	@Override
	public int getLevelCount(String articlescrapId, Judge_Level judge_level) {
		return mapper.getLevelCount(articlescrapId, judge_level);
	}

}
