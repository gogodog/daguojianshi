package com.dgjs.mapper.content;

import java.util.List;

import com.dgjs.model.persistence.RecommedArticlescrap;
import com.dgjs.model.persistence.condition.RecommedArticlescrapCondition;
import com.dgjs.model.persistence.enhance.RecommedArticlescrapEnhance;

public interface RecommedArticlescrapMapper {

	 public int save(RecommedArticlescrap recommedArticlescrap);
	 
	 public List<RecommedArticlescrapEnhance> list(RecommedArticlescrapCondition condition);

     public int deleteById(Long id);
}
