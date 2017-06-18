package com.dgjs.es.mapper.content;

import java.util.List;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;


public interface ArticlescrapMapper {

	public Articlescrap getArticlescrapIndex(String id);
	
	public PageInfoDto<Articlescrap> listArticlescrap(ArticlescrapCondtion condition);
	
	public int deleteById(String id);
	
	public int updateArticlescrapRecommend(String id,Integer sort,UpDown_Status status);
	
	public List<Articlescrap> listRecommend(UpDown_Status status);
	
	public int saveArticlescrap(Articlescrap articlescrap);
	
	public int updateArticlescrap(Articlescrap articlescrap) throws Exception;
	
	public List<Articlescrap> getArticlescrapByIds(String ids);
}
