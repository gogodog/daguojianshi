package com.dgjs.service.content;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;

public interface ArticlescrapService {

    public int saveArticlescrap(Articlescrap articlescrap);
	
	public int updateArticlescrap(Articlescrap articlescrap);
	
	public Articlescrap selectById(Long id);
	
	public PageInfoDto<Articlescrap> listArticlescrap(ArticlescrapCondtion articlescrapCondtion);
	
	public int deleteArticlescrap(Long id);
}
