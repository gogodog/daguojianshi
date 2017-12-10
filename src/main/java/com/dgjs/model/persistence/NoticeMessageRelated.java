package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.Message_Related_Type;

public class NoticeMessageRelated {

	private Long id;
	private Long notice_message_id;//通知消息id
	private Long related_id;//关联id
	private Message_Related_Type type;//类型
	private Date create_time;//创建时间
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNotice_message_id() {
		return notice_message_id;
	}
	public void setNotice_message_id(Long notice_message_id) {
		this.notice_message_id = notice_message_id;
	}
	public Message_Related_Type getType() {
		return type;
	}
	public void setType(Message_Related_Type type) {
		this.type = type;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Long getRelated_id() {
		return related_id;
	}
	public void setRelated_id(Long related_id) {
		this.related_id = related_id;
	}
	
}
