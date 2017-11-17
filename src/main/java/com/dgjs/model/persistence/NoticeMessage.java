package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.Read_Status;

public class NoticeMessage {

	private Long id;//id
	private Integer admin_id;//用户id
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	private String message;//消息
	private Read_Status status;//状态
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Read_Status getStatus() {
		return status;
	}
	public void setStatus(Read_Status status) {
		this.status = status;
	}
	
}
