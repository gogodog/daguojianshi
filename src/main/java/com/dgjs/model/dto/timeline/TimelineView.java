package com.dgjs.model.dto.timeline;

import com.dgjs.model.dto.BaseDto;

public class TimelineView{
	
	Timeline timeline;
	
	String maxTimeAid;
	
	String minTimeAid;

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
	
	
}
