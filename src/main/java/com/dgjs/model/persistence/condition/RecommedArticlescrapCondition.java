package com.dgjs.model.persistence.condition;

import com.dgjs.model.enums.UpDown_Status;

public class RecommedArticlescrapCondition {

	private UpDown_Status status;//状态查询，不传则全查
	private String title;//文章标题查询
	
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
