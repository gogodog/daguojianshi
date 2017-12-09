package com.dgjs.model.dto;

import com.dgjs.model.persistence.FrontFeedBack;

public class FrontFeedBackDto {
	
	FrontFeedBack feedBack;
	String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public FrontFeedBack getFeedBack() {
		return feedBack;
	}
	public void setFeedBack(FrontFeedBack feedBack) {
		this.feedBack = feedBack;
	}
	
}
