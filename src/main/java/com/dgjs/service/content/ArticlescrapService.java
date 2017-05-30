package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.model.persistence.enhance.RecommedArticlescrapEnhance;

public interface ArticlescrapService {

    public int saveArticlescrap(Articlescrap articlescrap);
	
	public int updateArticlescrap(Articlescrap articlescrap);
	
	public Articlescrap selectById(Long id);
	
	public PageInfoDto<Articlescrap> listArticlescrap(ArticlescrapCondtion articlescrapCondtion);
	
	public int deleteArticlescrap(Long id);
	
	public List<Articlescrap> getArticlescrapByComments(int number);
	
	public String getDadianArticlescrapIds(List<RecommedArticlescrapEnhance> recommedArticlescraps,List<Articlescrap> newArticlescraps,List<Articlescrap> commentsArticlescrap);
}
