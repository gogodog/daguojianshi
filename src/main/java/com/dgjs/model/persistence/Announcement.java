package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.Announcement_Type;
import com.dgjs.model.enums.UpDown_Status;

public class Announcement {

	private Integer id;//id
	private String message;//内容
	private UpDown_Status status;//状态
	private Announcement_Type type;//公告类型
	private Date create_time;//创建时间
	private Date show_time;//修改时间
    private Date update_time;//修改时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Announcement_Type getType() {
		return type;
	}
	public void setType(Announcement_Type type) {
		this.type = type;
	}
	public Date getShow_time() {
		return show_time;
	}
	public void setShow_time(Date show_time) {
		this.show_time = show_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}
