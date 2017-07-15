package com.dgjs.model.dto;

import com.dgjs.model.persistence.ChannelArticlescrap;

public class ChannelArticlescrapDto {

	private ChannelArticlescrap channelArticlescrap;
	
	private String title;//文章日志

	public ChannelArticlescrap getChannelArticlescrap() {
		return channelArticlescrap;
	}

	public void setChannelArticlescrap(ChannelArticlescrap channelArticlescrap) {
		this.channelArticlescrap = channelArticlescrap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
