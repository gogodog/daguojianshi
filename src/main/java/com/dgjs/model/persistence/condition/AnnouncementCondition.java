package com.dgjs.model.persistence.condition;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.dgjs.model.enums.Announcement_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.utils.DateUtils;

public class AnnouncementCondition extends BasePageCondition{

	private UpDown_Status status;//状态
	private Announcement_Type type;//公告类型
	private Date timeFrom;
	private Date timeTo;
	
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
	public Announcement_Type getType() {
		return type;
	}
	public void setType(Announcement_Type type) {
		this.type = type;
	}
	public Date getTimeFrom() {
		return timeFrom;
	}
	public Date getTimeTo() {
		return timeTo;
	}
	public void setTime_From(String time_From) {
		if(!StringUtils.isEmpty(time_From)){
			   this.timeFrom = DateUtils.parseDateFromString(time_From,DateUtils.DAY);
			}
	}
	public void setTime_To(String time_To) {
		if(!StringUtils.isEmpty(time_To)){
			   this.timeTo = DateUtils.parseDateFromString(time_To,DateUtils.DAY);
			}
	}
	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}
	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}
	
}
