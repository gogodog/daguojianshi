package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;

public interface ArticlescrapService {

    public int saveArticlescrap(Articlescrap articlescrap);
	
	public int updateArticlescrap(Articlescrap articlescrap) throws Exception;
	
	public Articlescrap selectByIdAll(String id);
	
	public Articlescrap selectById(String id);
	
	public String getContent(String id);
	
	public PageInfoDto<Articlescrap> listArticlescrap(ArticlescrapCondtion articlescrapCondtion);
	
	public int deleteArticlescrap(String id);
	
	public List<Articlescrap> getArticlescrapByComments(int number);
	
    public List<Articlescrap> getArticlescrapByIds(String[] ids);
	
	public String getDadianArticlescrapIds(List<Articlescrap> recommedArticlescraps,List<Articlescrap> newArticlescraps,List<Articlescrap> commentsArticlescrap);

	public int bulkUpdateStatus(List<Articlescrap> list,Articlescrap_Status status);
	
}
