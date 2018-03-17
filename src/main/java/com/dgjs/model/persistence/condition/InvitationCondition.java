package com.dgjs.model.persistence.condition;

import java.util.Date;
import java.util.List;

import com.dgjs.model.enums.Invitation_Status;


public class InvitationCondition extends BasePageCondition{

	private Date fromDate;
	private Date toDate;
	private Integer toUserId;
	private Integer fromUserId;
	private List<Invitation_Status> statusList;
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Integer getToUserId() {
		return toUserId;
	}
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}
	public Integer getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	public List<Invitation_Status> getStatusList() {
		return statusList;
	}
	public void setStatusList(List<Invitation_Status> statusList) {
		this.statusList = statusList;
	}
	
}
