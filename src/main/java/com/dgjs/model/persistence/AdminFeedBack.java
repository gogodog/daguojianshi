package com.dgjs.model.persistence;

import java.util.Date;

public class AdminFeedBack {

	private Long id;//id
	private Date create_time;//创建时间
	private String message;//反馈内容
	private Integer userId;//反馈人
	private boolean isHaveFeedBack;//是否已经回馈过
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public boolean isHaveFeedBack() {
		return isHaveFeedBack;
	}
	public void setHaveFeedBack(boolean isHaveFeedBack) {
		this.isHaveFeedBack = isHaveFeedBack;
	}
    
}
