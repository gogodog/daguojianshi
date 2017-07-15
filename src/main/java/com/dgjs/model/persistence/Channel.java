package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.UpDown_Status;

public class Channel {

	private Integer id;
	
	private String c_name;
	
	private int sort;
	
	private UpDown_Status status;

	private Date create_time;
	
	private Date update_time;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
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

	public UpDown_Status getStatus() {
		return status;
	}

	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
	
	
}
