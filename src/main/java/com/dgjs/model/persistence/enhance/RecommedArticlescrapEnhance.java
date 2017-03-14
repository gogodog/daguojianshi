package com.dgjs.model.persistence.enhance;

import com.dgjs.model.persistence.RecommedArticlescrap;

public class RecommedArticlescrapEnhance extends RecommedArticlescrap{

	private String title;//文章标题
	private String content;//文章内容
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
