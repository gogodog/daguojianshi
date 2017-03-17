package com.dgjs.model.persistence.enhance;

import com.dgjs.model.persistence.RecommedArticlescrap;

public class RecommedArticlescrapEnhance extends RecommedArticlescrap{

	private String title;//文章标题
	private String sub_content;//文章内容
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSub_content() {
		return sub_content;
	}
	public void setSub_content(String sub_content) {
		this.sub_content = sub_content;
	}
	
}
