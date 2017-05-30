package com.dgjs.mapper.content;

import java.util.List;

import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;

public interface ArticlescrapMapper {

	public int saveArticlescrap(Articlescrap articlescrap);
	
	public int updateArticlescrap(Articlescrap articlescrap);
	
	public Articlescrap selectById(Long id);
	
	public List<Articlescrap> selectByIds(List<Long> ids);
	
	public List<Articlescrap> listArticlescrap(ArticlescrapCondtion articlescrapCondtion);
	
	public int sizeListArticlescrap(ArticlescrapCondtion articlescrapCondtion);
	
	public int deleteArticlescrap(Long id);
	
}
