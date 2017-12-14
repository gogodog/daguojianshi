package com.dgjs.model.persistence.condition;

import java.util.Date;

import org.springframework.util.StringUtils;

import com.dgjs.constants.Constants;
import com.dgjs.model.enums.Announcement_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.utils.DateUtils;

public class AnnouncementCondition {

	private UpDown_Status status;//状态
	private Announcement_Type type;//公告类型
	private Date timeFrom;
	private Date timeTo;
	
	private int currentPage = 1;//当前页
	private boolean needTotalResults;//是否需要查询总数
	private int beginNum;
	private int onePageSize = Constants.DEFAULT_ONEPAGESIZE;
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
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public boolean isNeedTotalResults() {
		return needTotalResults;
	}
	public void setNeedTotalResults(boolean needTotalResults) {
		this.needTotalResults = needTotalResults;
	}
	public int getBeginNum() {
		return beginNum;
	}
	public void setBeginNum(int beginNum) {
		this.beginNum = beginNum;
	}
	public int getOnePageSize() {
		return onePageSize;
	}
	public void setOnePageSize(int onePageSize) {
		this.onePageSize = onePageSize;
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
