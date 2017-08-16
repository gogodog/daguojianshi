package com.dgjs.model.dto.timeline;


public class TimelineView{
	
	Timeline timeline;
	
	String maxTimeAid;
	
	String minTimeAid;
	
	int isHaveValue=1; //1:有值 
	
	int position=0;//1:到最早了，前面没有数据了 2:到最晚了，后面没有数据了

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}

	public String getMaxTimeAid() {
		return maxTimeAid;
	}

	public void setMaxTimeAid(String maxTimeAid) {
		this.maxTimeAid = maxTimeAid;
	}

	public String getMinTimeAid() {
		return minTimeAid;
	}

	public void setMinTimeAid(String minTimeAid) {
		this.minTimeAid = minTimeAid;
	}

	public int getIsHaveValue() {
		return isHaveValue;
	}

	public void setIsHaveValue(int isHaveValue) {
		this.isHaveValue = isHaveValue;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	
}
