package com.dgjs.model.dto;

import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.persistence.IndexConfig;

public class IndexConfigDto{
	
	private IndexConfig indexConfig;
	
	private Articlescrap articlescrap;
	
	private String[] pics;

	public String[] getPics() {
		return pics;
	}

	public void setPics(String[] pics) {
		this.pics = pics;
	}

	public IndexConfig getIndexConfig() {
		return indexConfig;
	}

	public void setIndexConfig(IndexConfig indexConfig) {
		this.indexConfig = indexConfig;
	}

	public Articlescrap getArticlescrap() {
		return articlescrap;
	}

	public void setArticlescrap(Articlescrap articlescrap) {
		this.articlescrap = articlescrap;
	}
	
	
}
