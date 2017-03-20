package com.dgjs.service.impl.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.RecommedArticlescrapMapper;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.RecommedArticlescrap;
import com.dgjs.model.persistence.condition.RecommedArticlescrapCondition;
import com.dgjs.model.persistence.enhance.RecommedArticlescrapEnhance;
import com.dgjs.service.content.RecommedArticlescrapService;

@Service
public class RecommedArticlescrapServiceImpl implements RecommedArticlescrapService{

	@Autowired
	private RecommedArticlescrapMapper mapper;
	
	@Override
	public int save(RecommedArticlescrap recommedArticlescrap) {
		return mapper.save(recommedArticlescrap);
	}

	@Override
	public List<RecommedArticlescrapEnhance> list(
			RecommedArticlescrapCondition condition) {
		List<RecommedArticlescrapEnhance> list=mapper.list(condition);
		return list;
	}

	@Override
	public int deleteById(Long id) {
		return mapper.deleteById(id);
	}

	@Override
	public RecommedArticlescrap selectByArticlescrapId(Long articlescrap_id) {
		return mapper.selectByArticlescrapId(articlescrap_id);
	}

	@Override
	public int updateStatus(Long id) {
		return mapper.updateStatus(id);
	}

}
