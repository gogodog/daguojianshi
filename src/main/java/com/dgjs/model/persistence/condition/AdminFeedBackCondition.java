package com.dgjs.model.persistence.condition;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.dgjs.utils.DateUtils;

public class AdminFeedBackCondition extends BasePageCondition{

	private Boolean isHaveReply;
	private Date timeFrom;
	private Date timeTo;
	
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
	public Boolean getIsHaveReply() {
		return isHaveReply;
	}
	public void setIsHaveReply(Boolean isHaveReply) {
		this.isHaveReply = isHaveReply;
	}
	
}
