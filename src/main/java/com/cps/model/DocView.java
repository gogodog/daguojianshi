package com.cps.model;

import java.util.Date;

public class DocView {
	private String id;
	private String name;
	private String status;
	private String srctype;
	private String category;
	private Date ts;
	private Date sh;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSrctype() {
		return srctype;
	}
	public void setSrctype(String srctype) {
		this.srctype = srctype;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	public Date getSh() {
		return sh;
	}
	public void setSh(Date sh) {
		this.sh = sh;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
