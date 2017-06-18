package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;

public interface ArticlescrapService {

    public int saveArticlescrap(Articlescrap articlescrap);
	
	public int updateArticlescrap(Articlescrap articlescrap) throws Exception;
	
	public Articlescrap selectById(String id);
	
	public PageInfoDto<Articlescrap> listArticlescrap(ArticlescrapCondtion articlescrapCondtion);
	
	public int deleteArticlescrap(String id);
	
	public List<Articlescrap> getArticlescrapByComments(int number);
	
	public String getDadianArticlescrapIds(List<Articlescrap> recommedArticlescraps,List<Articlescrap> newArticlescraps,List<Articlescrap> commentsArticlescrap);
}
