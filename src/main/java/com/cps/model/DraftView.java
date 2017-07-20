package com.cps.model;

import java.util.Date;

public class DraftView {
	private String id;
	private String name;
	private Date last;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getLast() {
		return last;
	}
	public void setLast(Date last) {
		this.last = last;
	}
	
}
