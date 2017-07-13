package com.dgjs.model.dto.timeline;

import java.util.List;

public class Timeline {
	String headline;
	String startDate;
	String text = "<p>大国简史，中国历史时间事件轴.</p>";
	String type = "default";
	Asset asset;
	List<Dat> date;
	public String getHeadline() {
		return headline;
	}
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Asset getAsset() {
		return asset;
	}
	public void setAsset(Asset asset) {
		this.asset = asset;
	}
	public List<Dat> getDate() {
		return date;
	}
	public void setDate(List<Dat> date) {
		this.date = date;
	}
}
