package com.dgjs.model.dto;

import com.dgjs.model.persistence.FeedBack;

public class FeedBackDto {
	
	FeedBack feedBack;
	String title;
	public FeedBack getFeedBack() {
		return feedBack;
	}
	public void setFeedBack(FeedBack feedBack) {
		this.feedBack = feedBack;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
