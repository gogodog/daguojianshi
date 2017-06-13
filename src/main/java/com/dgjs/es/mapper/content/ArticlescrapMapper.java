package com.dgjs.es.mapper.content;

import java.util.List;

import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;


public interface ArticlescrapMapper {

	public int saveOrUpdateArticlescrap(Articlescrap articlescrap) throws Exception;
	
	public Articlescrap getArticlescrapIndex(String id) throws Exception;
	
	public List<Articlescrap> listArticlescrap(ArticlescrapCondtion condition) throws Exception;
	
	public int deleteById(String id) throws Exception;
	
	public int updateArticlescrapRecommend(String id,int sort,UpDown_Status status) throws Exception;
	
	public List<Articlescrap> listRecommend(UpDown_Status status) throws Exception;
}
