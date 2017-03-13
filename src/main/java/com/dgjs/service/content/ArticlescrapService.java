package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;

public interface ArticlescrapService {

    public int saveArticlescrap(Articlescrap articlescrap);
	
	public int updateArticlescrap(Articlescrap articlescrap);
	
	public Articlescrap selectById(Long id);
	
	public List<Articlescrap> listArticlescrap(ArticlescrapCondtion articlescrapCondtion);
	
	public int deleteArticlescrap(Long id);
}
