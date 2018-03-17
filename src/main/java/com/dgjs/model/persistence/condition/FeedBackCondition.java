package com.dgjs.model.persistence.condition;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.dgjs.model.enums.Feedback_Type;
import com.dgjs.utils.DateUtils;

public class FeedBackCondition extends BasePageCondition{

	private String articlescrap_id;
	private Date timeFrom;
	private Date timeTo;
	private Feedback_Type feedback_type = Feedback_Type.FRONT;
	
	
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
	public Feedback_Type getFeedback_type() {
		return feedback_type;
	}
	public void setFeedback_type(Feedback_Type feedback_type) {
		this.feedback_type = feedback_type;
	}
	
}
