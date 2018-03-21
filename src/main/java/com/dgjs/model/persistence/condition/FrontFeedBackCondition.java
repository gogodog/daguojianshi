package com.dgjs.model.persistence.condition;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.dgjs.utils.DateUtils;

public class FrontFeedBackCondition extends BasePageCondition{
	
	private String articlescrap_id;
	private Date timeFrom;
	private Date timeTo;
	
	public String getArticlescrap_id() {
		return articlescrap_id;
	}
	public void setArticlescrap_id(String articlescrap_id) {
		this.articlescrap_id = articlescrap_id;
	}
	public Date getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}
	public Date getTimeTo() {
		return timeTo;
	}
	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}
	public void setTime_from(String time_from) {
		if(!StringUtils.isEmpty(time_from)){
			this.timeFrom = DateUtils.parseDateFromString(time_from, DateUtils.DAY);
		}
	}
	public void setTime_to(String time_to) {
		if(!StringUtils.isEmpty(time_to)){
			this.timeTo = DateUtils.parseDateFromString(time_to, DateUtils.DAY);
		}
	}
}
