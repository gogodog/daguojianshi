package com.dgjs.model.persistence;

import java.util.Date;

public class PrimaryKey {

	private Integer id;
	private Integer primary_value;
	private String p_desc;
	private Date create_time;
	private Date update_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPrimary_value() {
		return primary_value;
	}
	public void setPrimary_value(Integer primary_value) {
		this.primary_value = primary_value;
	}
	public String getP_desc() {
		return p_desc;
	}
	public void setP_desc(String p_desc) {
		this.p_desc = p_desc;
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
	
	
}
