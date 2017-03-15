package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.persistence.RecommedArticlescrap;
import com.dgjs.model.persistence.condition.RecommedArticlescrapCondition;
import com.dgjs.model.persistence.enhance.RecommedArticlescrapEnhance;

public interface RecommedArticlescrapService {

     public int save(RecommedArticlescrap recommedArticlescrap);
	 
	 public List<RecommedArticlescrapEnhance> list(RecommedArticlescrapCondition condition);

     public int deleteById(Long id);
    
     public RecommedArticlescrap selectByArticlescrapId(Long articlescrap_id);
}
